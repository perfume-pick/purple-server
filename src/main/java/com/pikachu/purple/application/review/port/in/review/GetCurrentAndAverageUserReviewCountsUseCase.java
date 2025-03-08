package com.pikachu.purple.application.review.port.in.review;


public interface GetCurrentAndAverageUserReviewCountsUseCase {

    Result findByUserId(Long userId);

    record Result(
        int currentUserReviewCounts,
        int averageUserReviewCounts
    ) {}

}
