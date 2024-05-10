package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.user.port.out.UserTokenRepository;
import com.pikachu.purple.infrastructure.redis.user.entity.UserJwtTokenRedisHash;
import com.pikachu.purple.infrastructure.redis.user.entity.UserRefreshTokenRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.UserJwtTokenRedisRepository;
import com.pikachu.purple.infrastructure.redis.user.repository.UserRefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTokenRedisAdapter implements UserTokenRepository {

    private final UserRefreshTokenRedisRepository userRefreshTokenRedisRepository;
    private final UserJwtTokenRedisRepository userJwtTokenRedisRepository;

    @Override
    public void saveRefreshToken(
        Long userId,
        String refreshToken,
        Long expirationSeconds
    ) {
        UserRefreshTokenRedisHash userRefreshTokenRedisHash = new UserRefreshTokenRedisHash(
            userId,
            refreshToken,
            expirationSeconds
        );

        userRefreshTokenRedisRepository.save(userRefreshTokenRedisHash);
    }

    @Override
    public void saveJwtToken(
        Long userId,
        String jwtToken,
        Long expirationSeconds
    ) {
        UserJwtTokenRedisHash userJwtTokenRedisHash = new UserJwtTokenRedisHash(
            userId,
            jwtToken,
            expirationSeconds
        );

        userJwtTokenRedisRepository.save(userJwtTokenRedisHash);
    }

}
