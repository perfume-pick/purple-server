package com.pikachu.purple.application.review.common.dto;

import lombok.Builder;

@Builder
public record UserReviewCountsDTO(
    int currentUserReviewCounts,
    int averageUserReviewCounts
) {}
