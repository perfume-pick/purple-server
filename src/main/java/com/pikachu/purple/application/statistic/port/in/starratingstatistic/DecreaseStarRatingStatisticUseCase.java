package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

public interface DecreaseStarRatingStatisticUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score
    ) {}

}
