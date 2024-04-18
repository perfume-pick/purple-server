package com.pikachu.purple.application.user.port.in;

public interface SendEmailVerificationUseCase {

    void invoke(String email);

}
