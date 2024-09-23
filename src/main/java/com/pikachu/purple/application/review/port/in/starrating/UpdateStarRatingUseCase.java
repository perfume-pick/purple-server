package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface UpdateStarRatingUseCase {

    Result invoke(Command command);

    record Command(
        Long perfumeId,
        int previousScore,
        int score
    ) {}

    record Result(StarRating starRating){}

}
