package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.like.DeleteAllLikeUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
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

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final DeleteStarRatingUseCase deleteStarRatingUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final DeleteAllLikeUseCase deleteAllLikeUseCase;

    @Transactional
    @Override
    public void invoke(Long reviewId) {
        Review review = reviewDomainService.find(reviewId);

        deleteStarRatingUseCase.invoke(review.getStarRating().getId());

        if(review.getType() == ReviewType.DETAIL) {
            ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.findAll(
                reviewId);

            reviewEvaluationDomainService.deleteAll(reviewId);

            decreaseEvaluationStatisticUseCase.invoke(
                review.getPerfume().getId(),
                reviewEvaluation
            );

            reviewDomainService.deleteReviewMoods(reviewId);
        }

        deleteAllLikeUseCase.invoke(reviewId);
        reviewDomainService.delete(reviewId);
    }

}
