package com.pikachu.purple.bootstrap.rating.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record RatingInfo(
    Long perfumeId,
    String perfumeName,
    @Min(value = 1, message = "최소 값은 1입니다.")
    @Max(value = 5, message = "최대 값은 5입니다.")
    int score
) {

}
