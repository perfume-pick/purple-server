package com.pikachu.purple.application.review.port.in.review;

public interface DeleteReviewUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {}

}
