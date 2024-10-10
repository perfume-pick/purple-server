package com.pikachu.purple.application.review.service.application.review;

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
public class DeleteReviewApplicationService implements DeleteReviewUseCase {

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final DeleteStarRatingUseCase deleteStarRatingUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {

        Review review = reviewDomainService.find(command.reviewId());

        deleteStarRatingUseCase.invoke(
            new DeleteStarRatingUseCase.Command(review.getStarRating().getId())
        );

        if(review.getType() == ReviewType.DETAIL) {
            ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.find(
                command.reviewId());

            reviewEvaluationDomainService.deleteAll(command.reviewId());

            decreaseEvaluationStatisticUseCase.invoke(new DecreaseEvaluationStatisticUseCase.Command(
                review.getPerfume().getId(),
                reviewEvaluation
            ));

            reviewDomainService.deleteReviewMoods(command.reviewId());
        }

        reviewDomainService.delete(command.reviewId());
    }

}
