package com.pikachu.purple.bootstrap.onboarding.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;

public record StarRatingVO(
    @NotNull
    @Positive
    Long perfumeId,

    @Range(min = 1, max = 5)
    int score
) {}
