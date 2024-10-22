package com.pikachu.purple.application.review.port.in.review;

public interface IncreaseLikeCountUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {}

}
