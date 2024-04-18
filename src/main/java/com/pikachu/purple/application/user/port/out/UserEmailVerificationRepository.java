package com.pikachu.purple.application.user.port.out;

public interface UserEmailVerificationRepository {

    void save(
        String email,
        String verifiedNumber,
        Long expirationTime
    );

}
