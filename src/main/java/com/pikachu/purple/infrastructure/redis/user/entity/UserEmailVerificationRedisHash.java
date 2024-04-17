package com.pikachu.purple.infrastructure.redis.user.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user_email_number")
public class UserEmailVerificationRedisHash implements Serializable {
    @Id
    private Long id;
    private String verifiedEmail;
    private String verifiedNumber;
    @TimeToLive
    private Long expirationTime;

    @Builder
    public UserEmailVerificationRedisHash(String verifiedEmail, String verifiedNumber, Long expirationTime){
        this.verifiedEmail = verifiedEmail;
        this.verifiedNumber = verifiedNumber;
        this.expirationTime = expirationTime;
    }

}
