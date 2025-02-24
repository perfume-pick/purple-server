package com.pikachu.purple.application.review.port.in.like;

public interface CreateLikeUseCase {

    void create(
        Long userId,
        Long reviewId
    );

}
