package com.pikachu.purple.application.user.service.util.strategy;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import com.pikachu.purple.application.user.port.out.SocialLoginPort;
import com.pikachu.purple.application.user.vo.SocialLoginTokenRequest;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import com.pikachu.purple.support.security.auth.util.JwtTokenProvider;
import com.pikachu.purple.support.security.auth.vo.JwtClaims;
import java.net.MalformedURLException;
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
    public Url getUrl() {
        return generateUrl(kakaoSocialLoginProperties.getAuthorizeUri());
    }

    @Override
    public SocialLoginToken getToken(String authorizationCode) {
        return socialLoginPort.getToken(
            new SocialLoginTokenRequest(
                GRANT_TYPE,
                kakaoSocialLoginProperties.getClientId(),
                kakaoSocialLoginProperties.getRedirectUri(),
                authorizationCode
            )
        );
    }

    private Url generateUrl(String socialLoginPath) {
        return new Url(
            socialLoginPath +
                QUERY_PARAMETER_PREFIX + "client_id=" + kakaoSocialLoginProperties.getClientId()
                +
                ENCODED_QUERY_PARAMETER_DELIMETER + "redirect_uri="
                + kakaoSocialLoginProperties.getRedirectUri() +
                ENCODED_QUERY_PARAMETER_DELIMETER + "response_type="
                + kakaoSocialLoginProperties.getResponseType()
        );
    }

    @Override
    public IdToken resolveIdToken(String idToken) throws MalformedURLException, JwkException {
        URL jwksUri = new URL(this.kakaoSocialLoginProperties.getJwksUri());

        JwtClaims result = jwtTokenProvider.verifyJwksBasedToken(idToken, jwksUri);

        String email = result.getCustomClaims().get("email").toString();

        return new IdToken(email);
    }
}
