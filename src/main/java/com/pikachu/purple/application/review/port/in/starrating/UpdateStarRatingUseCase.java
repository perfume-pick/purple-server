package com.pikachu.purple.application.review.port.in.starrating;

public interface UpdateStarRatingUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score
    ) {

    }

}
