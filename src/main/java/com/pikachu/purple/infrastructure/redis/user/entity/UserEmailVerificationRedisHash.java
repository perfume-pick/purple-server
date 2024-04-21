package com.pikachu.purple.infrastructure.redis.user.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_email_number")
public class UserEmailVerificationRedisHash implements Serializable {

    @Id
    private Long id;

    private String verifyEmail;

    @Indexed
    private String verifyCode;

    @TimeToLive
    private Long expirationTime;

    @Builder
    public UserEmailVerificationRedisHash(
        String verifyEmail,
        String verifyCode,
        Long expirationTime
    ){
        this.verifyEmail = verifyEmail;
        this.verifyCode = verifyCode;
        this.expirationTime = expirationTime;
    }

}
