package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.like.DeleteLikesUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.DecreaseEvaluationStatisticUseCase;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteReviewService implements DeleteReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewEvaluationRepository reviewEvaluationRepository;

    private final DeleteStarRatingUseCase deleteStarRatingUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final DeleteLikesUseCase deleteLikesUseCase;

    @Transactional
    @Override
    public void delete(Long reviewId) {
        Review review = reviewRepository.find(reviewId);

        deleteStarRatingUseCase.delete(review.getStarRating().getId());

        if(review.getType() == ReviewType.DETAIL) {
            ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find(reviewId);

            reviewEvaluationRepository.delete(reviewId);

            decreaseEvaluationStatisticUseCase.invoke(
                review.getPerfume().getId(),
                reviewEvaluation
            );

            reviewRepository.deleteReviewMoods(reviewId);
        }

        deleteLikesUseCase.deleteAll(reviewId);
        reviewRepository.delete(reviewId);
    }

}
