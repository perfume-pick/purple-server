package com.pikachu.purple.application.review.port.in.review;

public interface CreateSimpleReviewUseCase {

    void invoke(
        Long perfumeId,
        int score,
        String content
    );

}
