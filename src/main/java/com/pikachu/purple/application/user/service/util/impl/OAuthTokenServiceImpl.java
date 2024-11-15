package com.pikachu.purple.application.user.service.util.impl;

import com.pikachu.purple.application.user.port.out.OAuthRepository;
import com.pikachu.purple.application.user.service.util.OAuthTokenService;
import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthTokenServiceImpl implements OAuthTokenService {

    private final OAuthRepository oAuthRepository;

    @Override
    public void create(
        Long userId,
        String accessToken,
        String refreshToken,
        Integer refreshTokenExpiresIn
    ) {
        oAuthRepository.create(
            userId,
            accessToken,
            refreshToken,
            refreshTokenExpiresIn
        );
    }

    @Override
    public OAuthTokenRedisHash find(Long userId) {
        return oAuthRepository.find(userId);
    }

}
