package com.pikachu.purple.application.review.port.in.review;


public interface GetCurrentAndAverageUserReviewCountsUseCase {

    Result find(Long userId);

    record Result(
        int currentUserReviewCounts,
        int averageUserReviewCounts
    ) {}

}
