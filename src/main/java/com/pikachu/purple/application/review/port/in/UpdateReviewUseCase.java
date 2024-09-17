package com.pikachu.purple.application.review.port.in;

public interface UpdateReviewUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        Long ratingId,
        int score,
        String content
    ) {

    }

}
