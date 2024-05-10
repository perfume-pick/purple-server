package com.pikachu.purple.application.user.vo;

public record SocialLoginTokenRequest(
    String grantType,
    String clientId,
    String redirectUri,
    String authorizationCode
) {
}
