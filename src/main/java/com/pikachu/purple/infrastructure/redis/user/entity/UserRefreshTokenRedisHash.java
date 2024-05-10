package com.pikachu.purple.infrastructure.redis.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_refresh_token")
public class UserRefreshTokenRedisHash {

    @Id
    private Long id;

    private String refreshToken;

    @TimeToLive
    private Long expirationSeconds;

    public UserRefreshTokenRedisHash(Long id, String refreshToken, Long expirationSeconds) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.expirationSeconds = expirationSeconds;
    }

}
