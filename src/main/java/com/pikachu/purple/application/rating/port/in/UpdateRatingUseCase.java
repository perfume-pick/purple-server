package com.pikachu.purple.application.rating.port.in;

public interface UpdateRatingUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score
    ) {

    }

}
