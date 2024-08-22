package com.pikachu.purple.application.rating.port.in;

public interface RatingUpdateUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        Double score
    ) {

    }

}