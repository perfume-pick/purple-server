package com.pikachu.purple.bootstrap.auth.controller;

import com.pikachu.purple.application.user.port.in.SendEmailVerificationUseCase;
import com.pikachu.purple.bootstrap.auth.dto.request.EmailVerificationRequest;
import com.pikachu.purple.bootstrap.auth.api.AuthApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final SendEmailVerificationUseCase sendEmailVerificationUseCase;

    @Override
    public void send(@RequestBody @Valid EmailVerificationRequest request){
        sendEmailVerificationUseCase.invoke(request.getEmail());
    }
}
