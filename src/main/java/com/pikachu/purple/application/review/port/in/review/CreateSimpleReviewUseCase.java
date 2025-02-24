package com.pikachu.purple.application.review.port.in.review;

public interface CreateSimpleReviewUseCase {

    void create(
        Long userId,
        Long perfumeId,
        int score,
        String content
    );

}
