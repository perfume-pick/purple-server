package com.pikachu.purple.application.user.port.out;

import java.util.Optional;

public interface UserTokenRepository {

    void saveRefreshToken(
        Long userId,
        String refreshToken,
        Long expirationSeconds
    );

    void saveAccessToken(
        Long userId,
        String accessToken,
        Long expirationSeconds
    );

    Optional<String> findAccessTokenByUserId(
        Long userId
    );

    Optional<String> findRefreshTokenByUserId(
        Long userId
    );

}
