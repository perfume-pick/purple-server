package com.pikachu.purple.application.user.service.util.strategy;

import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import com.pikachu.purple.application.user.port.out.SocialLoginPort;
import com.pikachu.purple.application.user.vo.SocialLoginTokenRequest;
import com.pikachu.purple.application.user.vo.SocialRefreshTokenRequest;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import com.pikachu.purple.support.security.auth.util.JwtTokenProvider;
import com.pikachu.purple.support.security.auth.vo.JwtClaims;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConfigurationPropertiesScan("auth.kakao")
public class KakaoSocialLoginStrategy implements SocialLoginStrategy {

    private static final String QUERY_PARAMETER_PREFIX = "?";
    private static final String ENCODED_QUERY_PARAMETER_DELIMETER = "&";
    private static final String GRANT_TYPE = "authorization_code";

    private final KakaoSocialLoginProperties kakaoSocialLoginProperties;
    private final JwtTokenProvider jwtTokenProvider;

    private final SocialLoginPort socialLoginPort;

    @Override
    public URI getUrl(String frontUrl) throws URISyntaxException {
        return generateUrl(
            frontUrl,
            kakaoSocialLoginProperties.getAuthorizeUri()
        );
    }

    @Override
    public SocialLoginToken getToken(
        String authorizationCode,
        String frontUrl
    ) {
        return socialLoginPort.getToken(
            new SocialLoginTokenRequest(
                GRANT_TYPE,
                kakaoSocialLoginProperties.getClientId(),
                frontUrl + kakaoSocialLoginProperties.getRedirectUri(),
                authorizationCode
            )
        );
    }

    private URI generateUrl(
        String frontUrl,
        String socialLoginPath
    ) throws URISyntaxException {
        return new URI(
            socialLoginPath +
                QUERY_PARAMETER_PREFIX + "client_id=" + kakaoSocialLoginProperties.getClientId()
                +
                ENCODED_QUERY_PARAMETER_DELIMETER + "redirect_uri=" + frontUrl + kakaoSocialLoginProperties.getRedirectUri()
                +
                ENCODED_QUERY_PARAMETER_DELIMETER + "response_type=" + kakaoSocialLoginProperties.getResponseType()
        );
    }

    @Override
    public IdToken resolveIdToken(String idToken) {
        try {
            URL jwksUri = new URL(this.kakaoSocialLoginProperties.getJwksUri());

            JwtClaims result = jwtTokenProvider.verifyJwksBasedToken(idToken, jwksUri);

            String email = result.getCustomClaims().get("email").toString();
            return new IdToken(email);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("jwksUri 설정이 올바르지 않습니다.");
        }
    }

    @Override
    public Long logout(String accessToken, String refreshToken) {
        return socialLoginPort.logout(
            accessToken,
            new SocialRefreshTokenRequest(
                GRANT_TYPE,
                kakaoSocialLoginProperties.getClientId(),
                refreshToken
            )
        );
    }
}
