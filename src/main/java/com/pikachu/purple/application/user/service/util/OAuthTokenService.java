package com.pikachu.purple.application.user.service.util;

import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;

public interface OAuthTokenService {

    void create(Long userId, String accessToken, String refreshToken, Integer refreshTokenExpiresIn);

    OAuthTokenRedisHash find(Long userId);

}
