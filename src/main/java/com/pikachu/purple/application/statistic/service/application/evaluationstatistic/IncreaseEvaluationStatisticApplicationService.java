package com.pikachu.purple.application.statistic.service.application.evaluationstatistic;

import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IncreaseEvaluationStatisticApplicationService implements
    IncreaseEvaluationStatisticUseCase {

    private final EvaluationStatisticDomainService evaluationStatisticDomainService;

    @Override
    public void invoke(
        Long perfumeId,
        ReviewEvaluation reviewEvaluation
    ) {
        reviewEvaluation.getReviewIdSet().forEach(
            reviewId -> reviewEvaluation.getFields(reviewId).forEach(
                field -> reviewEvaluation.getOptions(reviewId, field).forEach(
                    option -> evaluationStatisticDomainService.increaseVotes(
                        perfumeId,
                        field,
                        option
                    )
                )
            )
        );
    }

}
