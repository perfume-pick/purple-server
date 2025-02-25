package com.pikachu.purple.bootstrap.user.dto.response;

import com.pikachu.purple.application.review.port.in.review.GetCurrentAndAverageUserReviewCountsUseCase;
import lombok.Getter;

@Getter
public class GetUserReviewCountsResponse {
    private final int currentUserReviewCounts;
    private final int averageUserReviewCounts;

    private GetUserReviewCountsResponse(
        int currentUserReviewCounts,
        int averageUserReviewCounts
    ) {
        this.currentUserReviewCounts = currentUserReviewCounts;
        this.averageUserReviewCounts = averageUserReviewCounts;
    }

    public static GetUserReviewCountsResponse of(
        GetCurrentAndAverageUserReviewCountsUseCase.Result result) {

        return new GetUserReviewCountsResponse(
            result.currentUserReviewCounts(),
            result.averageUserReviewCounts()
        );
    }


}
