package com.pikachu.purple.application.review.port.in.review;

public interface UpdateSimpleReviewUseCase {

    void update(
        Long reviewId,
        int score,
        String content
    );

}
