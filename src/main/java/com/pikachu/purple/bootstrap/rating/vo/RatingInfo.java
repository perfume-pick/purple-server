package com.pikachu.purple.bootstrap.rating.vo;

import org.hibernate.validator.constraints.Range;

public record RatingInfo(
    Long perfumeId,
    String perfumeName,
    @Range(min = 1, max = 5)
    int score
) {

}
