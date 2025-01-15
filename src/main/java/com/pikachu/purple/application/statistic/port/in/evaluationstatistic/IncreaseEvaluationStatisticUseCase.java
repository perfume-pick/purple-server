package com.pikachu.purple.application.statistic.port.in.evaluationstatistic;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface IncreaseEvaluationStatisticUseCase {

    void invoke(
        Long perfumeId,
        ReviewEvaluation reviewEvaluation
    );

}
