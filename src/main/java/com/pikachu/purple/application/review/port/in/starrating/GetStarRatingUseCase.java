package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface GetStarRatingUseCase {

    Result findByStarRatingId(
        Long starRatingId
    );

    Result findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );

    Result findByUserIdAndPerfumeIdWithPerfumeAndPerfumeAccords(
        Long userId,
        Long perfumeId
    );

    record Result(StarRating starRating) {}
}
