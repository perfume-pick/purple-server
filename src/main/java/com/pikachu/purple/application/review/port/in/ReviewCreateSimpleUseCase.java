package com.pikachu.purple.application.review.port.in;

public interface ReviewCreateSimpleUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score,
        String content
    ) {

    }

}
