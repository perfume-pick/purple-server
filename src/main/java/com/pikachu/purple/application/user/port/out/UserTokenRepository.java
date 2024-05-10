package com.pikachu.purple.application.user.port.out;

public interface UserTokenRepository {

    void saveRefreshToken(
        Long userId,
        String refreshToken,
        Long expirationSeconds
    );

    void saveJwtToken(
        Long userId,
        String jwtToken,
        Long expirationSeconds
    );

}
