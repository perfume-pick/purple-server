package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintsUseCase;
import com.pikachu.purple.application.review.port.in.like.GetLikesUseCase;
import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Like;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.review.enums.SortType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetReviewsService implements GetReviewsUseCase {

    private final GetPerfumeUseCase getPerfumeUseCase;
    private final GetReviewEvaluationUseCase getReviewEvaluationUseCase;
    private final GetMoodsUseCase getMoodsUseCase;
    private final GetComplaintsUseCase getComplaintsUseCase;
    private final GetLikesUseCase getLikesUseCase;

    private final ReviewRepository reviewRepository;

    @Override
    public Result findAll(ReviewType reviewType) {
        List<Review> reviews = reviewRepository.findAll(ReviewType.DETAIL);

        return new Result(reviews);
    }

    @Transactional
    @Override
    public Result findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLiked(
        Long currentUserId,
        Long perfumeId,
        String sortType
    ) {
        SortType getSortType = SortType.transByStr(sortType);

        Perfume perfume = getPerfumeUseCase.find(perfumeId).perfume();

        List<Review> reviews = new ArrayList<>();

        switch (getSortType) {
            case LIKED:
                reviews = reviewRepository.findAllWithPerfumeOrderByLikeCountDesc(
                    currentUserId,
                    perfume.getId()
                );
                break;
            case LATEST:
                reviews = reviewRepository.findAllWithPerfumeOrderByCreatedAtDesc(
                    currentUserId,
                    perfume.getId()
                );
                break;
            case STAR_RATING_HIGH:
                reviews = reviewRepository.findAllWithPerfumeOrderByScoreDesc(
                    currentUserId,
                    perfume.getId()
                );
                break;
            case STAR_RATING_LOW:
                reviews = reviewRepository.findAllWithPerfumeOrderByScoreAsc(
                    currentUserId,
                    perfume.getId()
                );
                break;
            default:
                break;
        }

        ReviewEvaluation reviewEvaluation = getReviewEvaluationUseCase.find(perfume)
            .reviewEvaluation();

        List<Complaint> currentUserComplaints = getComplaintsUseCase.findAll(
            currentUserId,
            perfumeId
        ).complaints();
        List<Like> currentUserLikes = getLikesUseCase.findAll(
            currentUserId,
            perfumeId
        ).likes();

        for (Review review : reviews) {
            review.setEvaluation(
                this.getReviewEvaluation(review.getId(), reviewEvaluation)
            );
            review.setMoods(
                getMoodsUseCase.findAll(review).moods()
            );

            for (Complaint complaint : currentUserComplaints) {
                if (Objects.equals(review.getId(), complaint.getReview().getId())) {
                    review.setComplained(true);
                }
            }
            for (Like like : currentUserLikes) {
                if (Objects.equals(review.getId(), like.getReview().getId())) {
                    review.setLiked(true);
                }
            }
        }

        return new Result(reviews);
    }

    private ReviewEvaluation getReviewEvaluation(Long reviewId, ReviewEvaluation reviewEvaluation) {
        ReviewEvaluation evaluation = new ReviewEvaluation();

        reviewEvaluation.getFields(reviewId).forEach(
            field -> {
                List<EvaluationOptionType> options = reviewEvaluation.getOptions(
                    reviewId,
                    field
                );
                evaluation.add(
                    reviewId,
                    field,
                    options
                );
            }
        );

        return evaluation;
    }

}
