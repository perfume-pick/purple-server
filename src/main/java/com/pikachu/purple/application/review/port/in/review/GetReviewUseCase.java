package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.Review;

public interface GetReviewUseCase {

    Result findByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    );

    Result findByUserIdAndPerfumeIdWithStarRatingAndEvaluationAndMoods(
        Long userId,
        Long perfumeId
    );

    record Result(Review review) {}

}
