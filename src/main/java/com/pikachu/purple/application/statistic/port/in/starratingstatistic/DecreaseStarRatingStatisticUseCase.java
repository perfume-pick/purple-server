package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

public interface DecreaseStarRatingStatisticUseCase {

    void invoke(
        Long perfumeId,
        int score
    );

}
