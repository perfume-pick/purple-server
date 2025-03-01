package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.complaint.GetComplaintsUseCase;
import com.pikachu.purple.application.review.port.in.like.GetLikesUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Like;
import com.pikachu.purple.domain.review.Review;
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

    private final GetComplaintsUseCase getComplaintsUseCase;
    private final GetLikesUseCase getLikesUseCase;

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Result findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLiked(
        Long currentUserId,
        Long perfumeId,
        String sortType
    ) {
        SortType getSortType = SortType.transByStr(sortType);

        List<Review> reviews = new ArrayList<>();

        switch (getSortType) {
            case LIKED:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsOrderByLikeCountDesc(
                    currentUserId,
                    perfumeId
                );
                break;
            case LATEST:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsOrderByCreatedAtDesc(
                    currentUserId,
                    perfumeId
                );
                break;
            case STAR_RATING_HIGH:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsOrderByScoreDesc(
                    currentUserId,
                    perfumeId
                );
                break;
            case STAR_RATING_LOW:
                reviews = reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodsOrderByScoreAsc(
                    currentUserId,
                    perfumeId
                );
                break;
            default:
                break;
        }

        List<Complaint> currentUserComplaints = getComplaintsUseCase.findAll(
            currentUserId,
            perfumeId
        ).complaints();
        List<Like> currentUserLikes = getLikesUseCase.findAll(
            currentUserId,
            perfumeId
        ).likes();

        for (Review review : reviews) {
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

}
