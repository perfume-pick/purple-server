package com.pikachu.purple.application.user.port.out;

public interface UserEmailVerificationRepository {

    void save(
        String email,
        String verificationCode,
        Long expirationTime
    );

    void confirm(
        String email,
        String verificationCode
    );

}
