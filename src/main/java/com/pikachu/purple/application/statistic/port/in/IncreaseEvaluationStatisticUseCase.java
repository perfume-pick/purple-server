package com.pikachu.purple.application.statistic.port.in;

public interface IncreaseEvaluationStatisticUseCase {
    void invoke(Command command);

    record Command(
        Long userId,
        Long perfumeId
    ) {}

}
