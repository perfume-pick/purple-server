package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface GetStarRatingUseCase {

    Result invoke(
        Long userId,
        Long perfumeId
    );

    record Result(StarRating starRating) {}
}
