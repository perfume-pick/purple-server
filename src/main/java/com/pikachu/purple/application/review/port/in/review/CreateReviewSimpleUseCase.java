package com.pikachu.purple.application.review.port.in.review;

public interface CreateReviewSimpleUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score,
        String content
    ) {}

}
