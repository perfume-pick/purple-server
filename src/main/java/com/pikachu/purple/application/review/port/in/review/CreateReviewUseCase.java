package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;

public interface CreateReviewUseCase {

    Result create(
        Long userId,
        Long perfumeId,
        String content,
        ReviewType reviewType
    );

    record Result(Review review) {}

}
