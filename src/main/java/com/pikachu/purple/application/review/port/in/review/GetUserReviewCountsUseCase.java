package com.pikachu.purple.application.review.port.in.review;


public interface GetUserReviewCountsUseCase {

    Result invoke();

    record Result(
        int currentUserReviewCounts,
        int averageUserReviewCounts
    ) {}

}
