package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import com.pikachu.purple.application.user.port.in.SocialLoginUseCase;
import com.pikachu.purple.application.user.port.in.UserSignUpUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.OAuthTokenService;
import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginApplicationService implements SocialLoginUseCase {

    public static final String QUERY_PARAMETER_PREFIX = "?";
    public static final String QUERY_PARAMETER_DELIMITER = "=";
    public static final String QUERY_PARAMETER_TOKEN_KEY = "token";

    private final KakaoSocialLoginProperties kakaoSocialLoginProperties;
    private final SocialLoginService socialLoginService;
    private final UserDomainService userDomainService;
    private final UserTokenService userTokenService;
    private final UserSignUpUseCase userSignUpUseCase;
    private final OAuthTokenService oAuthTokenService;

    @Override
    public Result invoke(Command command) {
        SocialLoginToken socialLoginToken = socialLoginService.getToken(
            command.socialLoginProvider(),
            command.authorizationCode(),
            command.frontUrl()
        );

        IdToken idTokenClaims = userTokenService.resolveIdToken(
            socialLoginToken.idToken(),
            command.socialLoginProvider()
        );

        String email = idTokenClaims.getEmail().replace("\"", "");

        User user = userDomainService.findByEmailAndSocialLoginProvider(
            email,
            command.socialLoginProvider()
        );

        boolean isSignUp = false;

        if (user == null) {
            isSignUp = true;
            userSignUpUseCase.invoke(
                new UserSignUpUseCase.Command(
                    email,
                    command.socialLoginProvider()
                )
            );

            user = userDomainService.findByEmailAndSocialLoginProvider(
                email,
                command.socialLoginProvider()
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

        return new SocialLoginUseCase.Result(
            jwtToken,
            isSignUp,
            user.getNickname(),
            user.getImageUrl()
        );
    }

}
