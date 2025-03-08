package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.SocialLoginUseCase;
import com.pikachu.purple.application.user.port.in.UserSignUpUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.OAuthTokenService;
import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class SocialLoginApplicationService implements SocialLoginUseCase {

    private final SocialLoginService socialLoginService;
    private final UserDomainService userDomainService;
    private final UserTokenService userTokenService;
    private final UserSignUpUseCase userSignUpUseCase;
    private final OAuthTokenService oAuthTokenService;

    @Override
    public Result invoke(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode,
        String frontUrl
    ) {
        SocialLoginToken socialLoginToken = socialLoginService.getToken(
            socialLoginProvider,
            authorizationCode,
            frontUrl
        );

        IdToken idTokenClaims = userTokenService.resolveIdToken(
            socialLoginToken.idToken(),
            socialLoginProvider
        );

        String email = idTokenClaims.getEmail().replace("\"", "");

        User user = userDomainService.findByEmailAndSocialLoginProvider(
            email,
            socialLoginProvider
        );

        boolean isSignUp = false;

        if (user == null) {
            isSignUp = true;
            userSignUpUseCase.invoke(
                email,
                socialLoginProvider
            );

            user = userDomainService.findByEmailAndSocialLoginProvider(
                email,
                socialLoginProvider
            );
        }

        oAuthTokenService.create(
            user.getId(),
            socialLoginToken.accessToken(),
            socialLoginToken.refreshToken(),
            socialLoginToken.refreshTokenExpiresIn()
        );

        String accessToken = userTokenService.generateAccessToken(user);
        String refreshToken = userTokenService.generateRefreshToken(user);

        String jwtToken = userTokenService.generateJwtToken(
            user,
            accessToken,
            refreshToken
        );

        return new Result(
            jwtToken,
            isSignUp,
            user.getNickname(),
            user.getImageUrl()
        );
    }

}
