package com.pikachu.purple.bootstrap.auth.controller;

import com.pikachu.purple.application.user.port.in.RefreshJwtTokenUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;
import com.pikachu.purple.application.user.port.in.SocialLoginUseCase;
import com.pikachu.purple.bootstrap.auth.api.AuthApi;
import com.pikachu.purple.bootstrap.auth.dto.request.RefreshJwtTokenRequest;
import com.pikachu.purple.bootstrap.auth.dto.response.RefreshJwtTokenResponse;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginResponse;
import com.pikachu.purple.bootstrap.auth.dto.response.SocialLoginTryResponse;
import com.pikachu.purple.bootstrap.common.dto.SuccessResponse;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
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
    public SuccessResponse<SocialLoginTryResponse> socialLoginTry(
        SocialLoginProvider socialLoginProvider)
        throws URISyntaxException {
        SocialLoginTryUseCase.Result result = socialLoginTryUseCase.invoke(
            new SocialLoginTryUseCase.Command(socialLoginProvider)
        );

        return SuccessResponse.of(
            new SocialLoginTryResponse(result.socialLoginUrl())
        );
    }

    @Override
    public SuccessResponse<SocialLoginResponse> socialLogin(
        SocialLoginProvider provider,
        String code
    ) {
        SocialLoginUseCase.Result result = socialLoginUseCase.invoke(
            new SocialLoginUseCase.Command(
                provider,
                code
            )
        );

        return SuccessResponse.of(
            new SocialLoginResponse(
                result.jwtToken(),
                result.isSignUp()
            )
        );
    }

    @Override
    public SuccessResponse<RefreshJwtTokenResponse> refreshJwtToken(
        RefreshJwtTokenRequest request) {
        RefreshJwtTokenUseCase.Result result = refreshJwtTokenUseCase.invoke(
            new RefreshJwtTokenUseCase.Command(
                request.jwtToken()
            )
        );

        return SuccessResponse.of(
            new RefreshJwtTokenResponse(result.jwtToken())
        );
    }
}
