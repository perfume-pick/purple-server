package com.pikachu.purple.application.rating.port.in;

public interface RatingCreateUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score
    ) {

    }

}
