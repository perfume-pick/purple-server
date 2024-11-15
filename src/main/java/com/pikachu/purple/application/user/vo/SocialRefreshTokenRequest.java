package com.pikachu.purple.application.user.vo;

public record SocialRefreshTokenRequest(
    String grantType,
    String clientId,
    String refreshToken
) {

}
