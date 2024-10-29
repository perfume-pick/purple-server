package com.pikachu.purple.application.review.common.dto;

import lombok.Builder;

@Builder
public record ReviewedBrandDTO(
    int order,
    String brandName,
    int reviewCounts
) {}
