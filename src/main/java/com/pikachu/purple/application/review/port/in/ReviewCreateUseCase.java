package com.pikachu.purple.application.review.port.in;

public interface ReviewCreateUseCase {

    void create(Command command);

    record Command(
        Long perfumeId,
        int score,
        String content
    ) {

    }

}
