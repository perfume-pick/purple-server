package com.pikachu.purple.application.rating.port.in;

public interface CreateStarRatingUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score
    ) {

    }

}
