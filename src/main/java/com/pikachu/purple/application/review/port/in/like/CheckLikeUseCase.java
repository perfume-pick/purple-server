package com.pikachu.purple.application.review.port.in.like;

public interface CheckLikeUseCase {

    Result check(
        Long userId,
        Long reviewId
    );

    record Result(boolean isLiked) {}

}
