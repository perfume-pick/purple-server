package com.pikachu.purple.bootstrap.onboarding.vo;

import org.hibernate.validator.constraints.Range;

public record StarRatingInfo(
    Long perfumeId,

    @Range(min = 1, max = 5)
    int score
) {

}
