package com.pikachu.purple.application.review.port.in.review;

public interface UpdateSimpleReviewUseCase {

    void invoke(
        Long reviewId,
        int score,
        String content
    );

}
