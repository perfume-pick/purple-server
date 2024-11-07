package com.pikachu.purple.application.review.port.in.like;

public interface DeleteAllLikeUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {}

}
