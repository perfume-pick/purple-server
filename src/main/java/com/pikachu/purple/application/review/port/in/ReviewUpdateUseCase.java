package com.pikachu.purple.application.review.port.in;

public interface ReviewUpdateUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        Long ratingId,
        int score,
        String content
    ) {

    }

}
