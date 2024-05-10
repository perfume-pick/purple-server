package com.pikachu.purple.domain.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SocialLoginToken(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("refresh_token") String refreshToken,
    @JsonProperty("id_token") String idToken,
    @JsonProperty("refresh_token_expires_in") Integer refreshTokenExpiresIn,
    @JsonProperty("expires_in") Integer expiresIn,
    @JsonProperty("scope") String scope,
    @JsonProperty("token_type") String tokenType) {
}
