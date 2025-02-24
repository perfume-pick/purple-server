package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface CreateOrUpdateStarRatingUseCase {

    Result invoke(
        Long userId,
        Long perfumeId,
        int score
    );

    record Result(StarRating starRating) {}

}
