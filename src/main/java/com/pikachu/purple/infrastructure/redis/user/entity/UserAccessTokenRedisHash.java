package com.pikachu.purple.infrastructure.redis.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_access_token")
public class UserAccessTokenRedisHash {

    @Id
    private Long id;

    private String accessToken;

    @TimeToLive
    private Long expirationSeconds;

    public UserAccessTokenRedisHash(Long id, String accessToken, Long expirationSeconds) {
        this.id = id;
        this.accessToken = accessToken;
        this.expirationSeconds = expirationSeconds;
    }

}
