package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
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
public class UpdateReviewSimpleApplicationService implements UpdateReviewSimpleUseCase {

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        Review review = reviewDomainService.findWithPerfume(command.reviewId());

        if(review.getType() == ReviewType.DETAIL) {
            ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.find(command.reviewId());
            reviewEvaluationDomainService.deleteAll(command.reviewId());
            decreaseEvaluationStatisticUseCase.invoke(new DecreaseEvaluationStatisticUseCase.Command(
                    review.getPerfume().getId(),
                    reviewEvaluation
                )
            );
            reviewDomainService.deleteReviewMoods(command.reviewId());
        }

        updateReviewUseCase.invoke(new UpdateReviewUseCase.Command(
            command.reviewId(),
            review.getPerfume().getId(),
            ReviewType.SIMPLE,
            command.content(),
            command.score()
            )
        );
    }

}
