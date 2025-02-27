package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface GetReviewsUseCase {

    Result findAll(
        Long userId,
        Long perfumeId,
        String sortType
    );

    record Result(List<Review> reviews) {}

}
