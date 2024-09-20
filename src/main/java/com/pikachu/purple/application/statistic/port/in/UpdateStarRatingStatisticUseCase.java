package com.pikachu.purple.application.statistic.port.in;

public interface UpdateStarRatingStatisticUseCase {

    void invoke(Command command);

    record Command(
        Long userId,
        Long perfumeId,
        int score
    ) {}

}
