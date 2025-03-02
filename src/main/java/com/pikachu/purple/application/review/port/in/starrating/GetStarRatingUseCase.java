package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface GetStarRatingUseCase {

    Result find(
        Long starRatingId
    );

    Result find(
        Long userId,
        Long perfumeId
    );

    Result findWithPerfumeAndPerfumeAccords(
        Long userId,
        Long perfumeId
    );

    record Result(StarRating starRating) {}
}
