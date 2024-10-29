package com.pikachu.purple.bootstrap.user.dto.response;

public record GetUserReviewCountsResponse(
    int currentUserReviewCounts,
    int averageUserReviewCounts
) {

}
