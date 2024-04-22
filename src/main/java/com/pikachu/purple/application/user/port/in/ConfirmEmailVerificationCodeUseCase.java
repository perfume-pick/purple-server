package com.pikachu.purple.application.user.port.in;

public interface ConfirmEmailVerificationCodeUseCase {

    void invoke(
        String email,
        String verificationCode
    );

}
