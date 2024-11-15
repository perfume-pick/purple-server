package com.pikachu.purple.infrastructure.redis.user.repository;

import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;

public interface OAuthTokenRedisRepository {

    void save(OAuthTokenRedisHash oAuthTokenRedisHash);

    OAuthTokenRedisHash find(Long userId);

}
