package com.pikachu.purple.application.rating.port.in;

import com.pikachu.purple.domain.review.StarRating;

public interface GetStarRatingUseCase {
    Result invoke(Command command);

    record Command(
        Long userId,
        Long perfumeId
    ) {}

    record Result(StarRating starRating) {}
}
