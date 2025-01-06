package com.pikachu.purple.infrastructure.persistence.statistic.vo;

import lombok.Builder;

@Builder
public record StarRatingStatisticCompositeKey(
    Long perfumeId,
    int score
) {}
