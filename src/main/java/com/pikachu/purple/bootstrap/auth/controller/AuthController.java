package com.pikachu.purple.bootstrap.auth.controller;

import com.pikachu.purple.application.user.port.in.SendEmailVerification;
import com.pikachu.purple.bootstrap.auth.dto.request.EmailVerificationRequest;
import com.pikachu.purple.bootstrap.auth.api.AuthApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perpicks/auth")
public class AuthController implements AuthApi {
    private final SendEmailVerification sendEmailVerification;

    @Override
    public ResponseEntity<Void> send(@RequestBody @Valid EmailVerificationRequest request){
        sendEmailVerification.invoke(request.getEmail());
        return ResponseEntity.noContent().build();
    }
}
