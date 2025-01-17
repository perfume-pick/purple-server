package com.pikachu.purple.bootstrap.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SocialLoginRequest(
    @NotBlank(message = "URL은 반드시 입력해야 합니다.")
    @Schema(description = "프론트엔드에서 전달하는 리다이렉션 URL", example = "http://localhost:3000")
    String frontUrl
) {}
