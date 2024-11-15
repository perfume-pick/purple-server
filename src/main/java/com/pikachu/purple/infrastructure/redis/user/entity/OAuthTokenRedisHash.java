package com.pikachu.purple.infrastructure.redis.user.entity;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "oauth_token")
public class OAuthTokenRedisHash {

    @Id
    private Long id;

    private String accessToken;

    private String refreshToken;

    @TimeToLive
    private Long expirationSeconds;

    public OAuthTokenRedisHash(
        Long userId,
        String accessToken,
        String refreshToken,
        Long expirationSeconds
    ) {
        this.id = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expirationSeconds = expirationSeconds;
    }

}
