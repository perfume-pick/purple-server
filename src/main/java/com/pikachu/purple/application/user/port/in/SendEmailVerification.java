package com.pikachu.purple.application.user.port.in;


public interface SendEmailVerification {
    void invoke(String email);

}
