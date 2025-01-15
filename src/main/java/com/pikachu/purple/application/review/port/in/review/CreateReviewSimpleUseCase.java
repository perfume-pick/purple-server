package com.pikachu.purple.application.review.port.in.review;

public interface CreateReviewSimpleUseCase {

    void invoke(
        Long perfumeId,
        int score,
        String content
    );

}
