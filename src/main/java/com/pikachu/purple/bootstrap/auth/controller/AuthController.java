package com.pikachu.purple.bootstrap.auth.controller;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.user.port.in.RefreshJwtTokenUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginUseCase;
import com.pikachu.purple.bootstrap.auth.api.AuthApi;
import com.pikachu.purple.bootstrap.auth.dto.request.RefreshJwtTokenRequest;
import com.pikachu.purple.bootstrap.auth.dto.response.RefreshJwtTokenResponse;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final SocialLoginTryUseCase socialLoginTryUseCase;
    private final SocialLoginUseCase socialLoginUseCase;
    private final RefreshJwtTokenUseCase refreshJwtTokenUseCase;

    @Override
    public SocialLoginTryResponse socialLoginTry(SocialLoginProvider socialLoginProvider)
        throws URISyntaxException {
        SocialLoginTryUseCase.Result result = socialLoginTryUseCase.invoke(
            new SocialLoginTryUseCase.Command(socialLoginProvider)
        );

        return new SocialLoginTryResponse(result.socialLoginUrl());
    }

    @Override
    public void socialLogin(
        String provider,
        String code,
        HttpServletResponse response
    ) throws IOException, JwkException, URISyntaxException {
        SocialLoginUseCase.Result result = socialLoginUseCase.invoke(
            new SocialLoginUseCase.Command(
                SocialLoginProvider.of(provider),
                code
            )
        );

        response.sendRedirect(result.socialLoginSuccessUrl());
    }

    @Override
    public RefreshJwtTokenResponse refreshJwtToken(RefreshJwtTokenRequest request) {
        RefreshJwtTokenUseCase.Result result = refreshJwtTokenUseCase.invoke(
            new RefreshJwtTokenUseCase.Command(
                request.jwtToken()
            )
        );

        return new RefreshJwtTokenResponse(result.jwtToken());
    }
}
