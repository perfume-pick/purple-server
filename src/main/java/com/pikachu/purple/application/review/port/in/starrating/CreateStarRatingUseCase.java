package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface CreateStarRatingUseCase {

    Result create(
        Long userId,
        Long perfumeId,
        int score
    );

    record Result(StarRating starRating) {}

}
