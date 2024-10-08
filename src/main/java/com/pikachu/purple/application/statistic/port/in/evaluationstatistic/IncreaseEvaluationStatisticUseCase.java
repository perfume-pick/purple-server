package com.pikachu.purple.application.statistic.port.in.evaluationstatistic;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface IncreaseEvaluationStatisticUseCase {
    void invoke(Command command);

    record Command(
        Long perfumeId,
        ReviewEvaluation reviewEvaluation
    ) {}

}
