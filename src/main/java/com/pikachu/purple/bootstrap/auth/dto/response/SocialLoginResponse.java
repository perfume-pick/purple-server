package com.pikachu.purple.bootstrap.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "SocialLoginResponse",
    description = "소셜 로그인 API 응답"
)
public record SocialLoginResponse(
    @Schema(description = "jwt token")
    String jwtToken,
    @Schema(description = "최초 로그인(회원가입) 여부")
    boolean isSignUp,
    String nickname,
    String imageUrl
) {
}
