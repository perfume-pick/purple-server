package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;

public interface OAuthRepository {

    void create(Long userId, String accessToken, String refreshToken, Integer refreshTokenExpiresIn);

    OAuthTokenRedisHash find(Long userId);

}
