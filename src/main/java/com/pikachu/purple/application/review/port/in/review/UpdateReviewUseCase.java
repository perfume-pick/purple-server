package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.enums.ReviewType;

public interface UpdateReviewUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        Long perfumeId,
        ReviewType reviewType,
        String content,
        int score
    ) {}

}
