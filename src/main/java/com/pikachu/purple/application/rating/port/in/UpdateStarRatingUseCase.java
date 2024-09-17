package com.pikachu.purple.application.rating.port.in;

public interface UpdateStarRatingUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score
    ) {

    }

}
