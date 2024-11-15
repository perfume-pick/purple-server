package com.pikachu.purple.infrastructure.redis.user.adapter;

import com.pikachu.purple.application.user.port.out.OAuthRepository;
import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;
import com.pikachu.purple.infrastructure.redis.user.repository.OAuthTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthTokenRedisAdapter implements OAuthRepository {

    private final OAuthTokenRedisRepository oAuthTokenRedisRepository;

    public void create(
        Long userId,
        String accessToken,
        String refreshToken,
        Integer refreshTokenExpiresIn
    ) {
        OAuthTokenRedisHash oAuthTokenRedisHash = new OAuthTokenRedisHash(
            userId,
            accessToken,
            refreshToken,
            Long.valueOf(refreshTokenExpiresIn)
        );

        oAuthTokenRedisRepository.save(oAuthTokenRedisHash);
    }

    @Override
    public OAuthTokenRedisHash find(Long userId) {
        return oAuthTokenRedisRepository.find(userId);
    }

}
