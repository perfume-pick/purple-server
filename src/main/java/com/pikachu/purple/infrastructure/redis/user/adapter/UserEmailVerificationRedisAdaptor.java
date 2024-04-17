package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.infrastructure.redis.user.entity.UserEmailVerificationRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserEmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmailVerificationRedisAdaptor implements
    com.pikachu.purple.application.user.port.out.UserEmailVerificationRepository {
    private final UserEmailVerificationRepository userEmailVerificationRepository;

    @Override
    public void save(String email, String verifiedNumber, Long expirationTime){
        UserEmailVerificationRedisHash hash = UserEmailVerificationRedisHash.builder()
            .verifiedEmail(email)
            .verifiedNumber(verifiedNumber)
            .expirationTime(expirationTime)
            .build();

        userEmailVerificationRepository.save(hash);
    }

}
