package com.pikachu.purple.application.review.service.application.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.application.review.util.ReviewEvaluationConverter;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReviewEvaluationApplicationService implements CreateReviewEvaluationUseCase {

    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;

    @Override
    public void invoke(Command command) {

        Review review = command.review();
        ReviewEvaluation reviewEvaluation = ReviewEvaluationConverter.of(
            review.getId(),
            command.evaluationFieldVOs()
        );

        reviewEvaluationDomainService.createAll(reviewEvaluation);

        increaseEvaluationStatisticUseCase.invoke(
            new IncreaseEvaluationStatisticUseCase.Command(
                review.getPerfume().getId(),
                reviewEvaluation
            )
        );
    }
    
}
