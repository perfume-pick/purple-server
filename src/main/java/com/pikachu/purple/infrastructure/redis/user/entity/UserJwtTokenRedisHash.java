package com.pikachu.purple.infrastructure.redis.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_jwt_token")
public class UserJwtTokenRedisHash {

    @Id
    private Long id;

    private String jwtToken;

    @TimeToLive
    private Long expirationSeconds;

    public UserJwtTokenRedisHash(Long id, String jwtToken, Long expirationSeconds) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.expirationSeconds = expirationSeconds;
    }

}
