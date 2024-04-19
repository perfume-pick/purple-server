package com.pikachu.purple.bootstrap.auth.controller;

import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase.Command;
import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase.Result;
import com.pikachu.purple.bootstrap.auth.api.AuthApi;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import com.pikachu.purple.application.user.port.in.SendEmailVerificationUseCase;
import com.pikachu.purple.bootstrap.auth.dto.request.EmailVerificationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final SocialLoginTryUseCase socialLoginTryUseCase;
    private final SendEmailVerificationUseCase sendEmailVerificationUseCase;

    @Override
    public SocialLoginTryResponse loginTry(SocialLoginProvider socialLoginProvider) {
        Result result = socialLoginTryUseCase.invoke(
            new Command(socialLoginProvider)
        );

        return new SocialLoginTryResponse(result.socialLoginUri());
    }

    @Override
    public void send(@RequestBody @Valid EmailVerificationRequest request){
        sendEmailVerificationUseCase.invoke(request.getEmail());
    }
}
