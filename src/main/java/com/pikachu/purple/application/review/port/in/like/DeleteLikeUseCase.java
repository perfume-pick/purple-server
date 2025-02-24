package com.pikachu.purple.application.review.port.in.like;

public interface DeleteLikeUseCase {

    void delete(
        Long userId,
        Long reviewId
    );

}
