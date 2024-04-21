package com.pikachu.purple.infrastructure.redis.user.adapter;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.InvalidVerifyCodeException;

import com.pikachu.purple.application.user.port.out.UserEmailVerificationRepository;
import com.pikachu.purple.infrastructure.redis.user.entity.UserEmailVerificationRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserEmailVerificationRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmailVerificationRedisAdaptor implements UserEmailVerificationRepository {

    private final UserEmailVerificationRedisRepository userEmailVerificationRedisHash;

    @Override
    public void save(
        String email,
        String verifyCode,
        Long expirationTime
    ){
        UserEmailVerificationRedisHash hash = UserEmailVerificationRedisHash.builder()
            .verifyEmail(email)
            .verifyCode(verifyCode)
            .expirationTime(expirationTime)
            .build();

        userEmailVerificationRedisHash.save(hash);
    }

    @Override
    public void confirm(
        String email,
        String verifyCode
    ){
        userEmailVerificationRedisHash.findByVerifyCode(verifyCode)
            .filter(userEmailVerification -> email.equals(userEmailVerification.getVerifyEmail()))
            .orElseThrow(() -> InvalidVerifyCodeException);
    }

}
