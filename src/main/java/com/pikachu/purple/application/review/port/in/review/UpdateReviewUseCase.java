package com.pikachu.purple.application.review.port.in.review;

public interface UpdateReviewUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        int score,
        String content
    ) {

    }

}
