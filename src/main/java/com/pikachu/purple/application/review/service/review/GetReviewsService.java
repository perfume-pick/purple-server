package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintsUseCase;
import com.pikachu.purple.application.review.port.in.like.CheckLikeUseCase;
import com.pikachu.purple.application.review.port.in.like.GetLikesUseCase;
import com.pikachu.purple.application.review.port.in.mood.GetMoodsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.like.CheckLikeService;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Like;
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

    private final GetStarRatingUseCase getStarRatingUseCase;
    private final GetPerfumeUseCase getPerfumeUseCase;
    private final GetReviewEvaluationUseCase getReviewEvaluationUseCase;
    private final GetMoodsUseCase getMoodsUseCase;
    private final GetComplaintsUseCase getComplaintsUseCase;
    private final GetLikesUseCase getLikesUseCase;
    private final CheckLikeUseCase checkLikeUseCase;

    private final ReviewRepository reviewRepository;

    @Override
    public Result findAll(ReviewType reviewType) {
        List<Review> reviews = reviewRepository.findAll(ReviewType.DETAIL);

        return new Result(reviews);
    }

    @Override
    public Result findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsLiked(
        Long userId,
        String sortType
    ) {
        SortType getSortType = SortType.transByStr(sortType);

        List<Review> reviews = new ArrayList<>();
        switch (getSortType) {
            case LIKED:
                reviews = reviewRepository.findAllOrderByLikeCountDesc(userId);
                break;
            case LATEST:
                reviews = reviewRepository.findAllOrderByCreatedAtDesc(userId);
                break;
            case STAR_RATING_HIGH:
                reviews = reviewRepository.findAllOrderByScoreDesc(userId);
                break;
            case STAR_RATING_LOW:
                reviews = reviewRepository.findAllOrderByScoreAsc(userId);
                break;
            default:
                break;
        }


        for (Review review : reviews) {
            review.setStarRating(
                getStarRatingUseCase.find(review.getStarRating().getId()).starRating()
            );
            review.setPerfume(
                getPerfumeUseCase.find(review.getPerfume().getId()).perfume()
            );
            review.setEvaluation(
                getReviewEvaluationUseCase.find(review).reviewEvaluation()
            );
            review.setMoods(
                getMoodsUseCase.findAll(review).moods()
            );
            review.setLiked(
                checkLikeUseCase.check(
                    userId,
                    review.getId()
                ).isLiked()
            );
        }

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
                reviews = reviewRepository.findAllOrderByLikeCountDesc(perfume);
                break;
            case LATEST:
                reviews = reviewRepository.findAllOrderByCreatedAtDesc(perfume);
                break;
            case STAR_RATING_HIGH:
                reviews = reviewRepository.findAllOrderByScoreDesc(perfume);
                break;
            case STAR_RATING_LOW:
                reviews = reviewRepository.findAllOrderByScoreAsc(perfume);
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
            review.setPerfume(perfume);
            review.setEvaluation(
                this.getReviewEvaluation(review.getId(), reviewEvaluation)
            );
            review.setMoods(
                getMoodsUseCase.findAll(review).moods()
            );
            review.setStarRating(
                getStarRatingUseCase
                    .find(review.getStarRating().getId())
                    .starRating()
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
