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
        String verificationCode,
        Long expirationTime
    ) {
        UserEmailVerificationRedisHash hash = UserEmailVerificationRedisHash.builder()
            .email(email)
            .verificationCode(verificationCode)
            .expirationTime(expirationTime)
            .build();

        userEmailVerificationRedisHash.save(hash);
    }

    @Override
    public void confirm(
        String email,
        String verificationCode
    ) {
        userEmailVerificationRedisHash.findById(email)
            .filter(userEmailVerificationInfo -> verificationCode.equals(
                userEmailVerificationInfo.getVerificationCode())
            )
            .orElseThrow(() -> InvalidVerifyCodeException);
    }

}
