package com.pikachu.purple.application.statistic.port.in.evaluationstatistic;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface DecreaseEvaluationStatisticUseCase {
    void invoke(Command command);

    record Command(
        Long perfumeId,
        ReviewEvaluation reviewEvaluation
    ) {}

}