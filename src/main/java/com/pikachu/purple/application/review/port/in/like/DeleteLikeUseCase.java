package com.pikachu.purple.application.review.port.in.like;

public interface DeleteLikeUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {}

}
