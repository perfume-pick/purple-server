package com.pikachu.purple.application.user.service.application;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import com.pikachu.purple.application.user.port.in.SocialLoginUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.net.MalformedURLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialLoginApplicationService implements SocialLoginUseCase {

    public static final String QUERY_PARAMETER_PREFIX = "?";
    public static final String QUERY_PARAMETER_TOKEN_KEY = "token";

    private final KakaoSocialLoginProperties kakaoSocialLoginProperties;
    private final SocialLoginService socialLoginService;
    private final UserDomainService userDomainService;
    private final UserTokenService userTokenService;

    @Override
    public Result invoke(Command command) throws MalformedURLException, JwkException {
        SocialLoginToken socialLoginToken = socialLoginService.getToken(
            command.socialLoginProvider(),
            command.authorizationCode()
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

        if (user == null) {
            // 회원가입 로직
        }

        String accessToken = userTokenService.generateAccessToken(user);
        String refreshToken = userTokenService.generateRefreshToken(user);

        String jwtToken = userTokenService.generateJwtToken(
            user,
            accessToken,
            refreshToken
        );

        return new SocialLoginUseCase.Result(
            generateLoginSuccessUrl(jwtToken)
        );
    }

    private Url generateLoginSuccessUrl(String jwtToken) {
        String redirectUri = kakaoSocialLoginProperties.getLoginSuccessUri();

        String loginSuccessUri = redirectUri + QUERY_PARAMETER_PREFIX + QUERY_PARAMETER_TOKEN_KEY + jwtToken;

        return new Url(loginSuccessUri);
    }

}
