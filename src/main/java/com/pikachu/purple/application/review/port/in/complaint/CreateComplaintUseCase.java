package com.pikachu.purple.application.review.port.in.complaint;

public interface CreateComplaintUseCase {

    void create(
        Long userId,
        Long reviewId
    );

}
