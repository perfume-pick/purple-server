package com.pikachu.purple.application.statistic.service.application.evaluationstatistic;

import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.DecreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecreaseEvaluationStatisticApplicationService implements
    DecreaseEvaluationStatisticUseCase {

    private final EvaluationStatisticDomainService evaluationStatisticDomainService;

    @Override
    public void invoke(Command command) {
        ReviewEvaluation reviewEvaluation = command.reviewEvaluation();
        reviewEvaluation.getReviewIdSet().forEach(
            reviewId -> reviewEvaluation.getFields(reviewId).forEach(
                field -> reviewEvaluation.getOptions(reviewId, field).forEach(
                    option -> evaluationStatisticDomainService.decreaseVotes(
                        command.perfumeId(),
                        field,
                        option
                    )
                )
            )
        );

    }

}
