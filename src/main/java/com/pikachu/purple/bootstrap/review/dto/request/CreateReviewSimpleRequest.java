package com.pikachu.purple.bootstrap.review.dto.request;

import com.pikachu.purple.application.util.IdUtil;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record CreateReviewSimpleRequest(
    Long perfumeId,
    @Range(min = 1, max = 5)
    int score,
    @Size(min = 10, max = 300, message = "최소 10자 ~ 최대 300자 입력할 수 있습니다.")
    String content
) {}
