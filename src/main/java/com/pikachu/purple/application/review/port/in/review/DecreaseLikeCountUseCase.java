package com.pikachu.purple.application.review.port.in.review;

public interface DecreaseLikeCountUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {}

}
