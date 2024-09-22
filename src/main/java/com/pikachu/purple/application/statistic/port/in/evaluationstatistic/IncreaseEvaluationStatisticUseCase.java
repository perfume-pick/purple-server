package com.pikachu.purple.application.statistic.port.in.evaluationstatistic;

public interface IncreaseEvaluationStatisticUseCase {
    void invoke(Command command);

    record Command(
        Long userId,
        Long perfumeId
    ) {}

}
