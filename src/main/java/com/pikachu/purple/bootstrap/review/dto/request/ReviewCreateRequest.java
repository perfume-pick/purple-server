package com.pikachu.purple.bootstrap.review.dto.request;

import jakarta.validation.constraints.Size;

public record ReviewCreateRequest(
    Long perfumeId,
    Double score,
    @Size(min = 10, max = 300, message = "최소 10자 ~ 최대 300자 입력할 수 있습니다.") String content
) {

}
