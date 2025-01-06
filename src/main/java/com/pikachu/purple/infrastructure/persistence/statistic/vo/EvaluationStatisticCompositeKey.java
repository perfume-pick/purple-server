package com.pikachu.purple.infrastructure.persistence.statistic.vo;

import lombok.Builder;

@Builder
public record EvaluationStatisticCompositeKey(
    Long perfumeId,
    String fieldCode,
    String optionCode
) {}
