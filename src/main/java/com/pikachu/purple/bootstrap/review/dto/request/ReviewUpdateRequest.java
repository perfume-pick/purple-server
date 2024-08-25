package com.pikachu.purple.bootstrap.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ReviewUpdateRequest (
    Long ratingId,
    @Min(value = 1, message = "최소 값은 1입니다.")
    @Max(value = 5, message = "최대 값은 5입니다.")
    int score,
    @Size(min = 10, max = 300, message = "최소 10자 ~ 최대 300자 입력할 수 있습니다.")
    String content
) {

}
