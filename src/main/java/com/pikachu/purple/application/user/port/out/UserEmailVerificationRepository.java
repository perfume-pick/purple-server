package com.pikachu.purple.application.user.port.out;

public interface UserEmailVerificationRepository {

    void save(
        String email,
        String verifyCode,
        Long expirationTime
    );

    void confirm(
        String email,
        String verifyCode
    );

}
