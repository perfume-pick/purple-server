package com.pikachu.purple.bootstrap.onboarding.vo;

import com.pikachu.purple.application.util.IdUtil;
import org.hibernate.validator.constraints.Range;

public record StarRatingVO(
    Long perfumeId,

    @Range(min = 1, max = 5)
    int score
) {

    public StarRatingVO(
        String perfumeId,
        int score
    ) {
        this(IdUtil.from(perfumeId), score);
    }

}
