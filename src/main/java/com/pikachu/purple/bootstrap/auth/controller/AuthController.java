package com.pikachu.purple.bootstrap.auth.controller;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.user.port.in.SendEmailVerificationUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginUseCase;
import com.pikachu.purple.bootstrap.auth.api.AuthApi;
import com.pikachu.purple.bootstrap.auth.dto.request.EmailVerificationRequest;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final SocialLoginTryUseCase socialLoginTryUseCase;
    private final SocialLoginUseCase socialLoginUseCase;
    private final SendEmailVerificationUseCase sendEmailVerificationUseCase;

    @Override
    public SocialLoginTryResponse socialLoginTry(SocialLoginProvider socialLoginProvider) {
        SocialLoginTryUseCase.Result result = socialLoginTryUseCase.invoke(
            new SocialLoginTryUseCase.Command(socialLoginProvider)
        );

        return new SocialLoginTryResponse(result.socialLoginUrl().getValue());
    }

    @Override
    public void socialLogin(
        SocialLoginProvider provider,
        String code,
        HttpServletResponse response
    ) throws IOException, JwkException {
        SocialLoginUseCase.Result result = socialLoginUseCase.invoke(
            new SocialLoginUseCase.Command(
                provider,
                code
            )
        );

        response.sendRedirect(result.socialLoginSuccessUrl().getValue());
    }

    @Override
    public void send(@RequestBody @Valid EmailVerificationRequest request){
        sendEmailVerificationUseCase.invoke(request.getEmail());
    }

}
