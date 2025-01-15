package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

public interface IncreaseStarRatingStatisticUseCase {

    void invoke(
        Long perfumeId,
        int score
    );

}
