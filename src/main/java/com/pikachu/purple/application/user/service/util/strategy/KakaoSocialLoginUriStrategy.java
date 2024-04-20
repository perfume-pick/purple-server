package com.pikachu.purple.application.user.service.util.strategy;

import com.pikachu.purple.application.common.properties.KakaoSocialLoginProperties;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConfigurationPropertiesScan("auth.kakao")
public class KakaoSocialLoginUriStrategy implements SocialLoginUriStrategy {

    private static final String QUERY_PARAMETER_PREFIX = "?";
    private static final String ENCODED_QUERY_PARAMETER_DELIMETER = "&";

    private final KakaoSocialLoginProperties kakaoSocialLoginProperties;

    @Override
    public URI getUri() {
        String defaultSocialLoginUri = kakaoSocialLoginProperties.getAuthorizeUri();
        return generateUri(defaultSocialLoginUri);
    }

    private URI generateUri(String socialLoginPath) {
        try {
            return new URI(
                socialLoginPath +
                    QUERY_PARAMETER_PREFIX + "client_id=" + kakaoSocialLoginProperties.getClientId()
                    +
                    ENCODED_QUERY_PARAMETER_DELIMETER + "redirect_uri="
                    + kakaoSocialLoginProperties.getRedirectUri() +
                    ENCODED_QUERY_PARAMETER_DELIMETER + "response_type="
                    + kakaoSocialLoginProperties.getResponseType()
            );
        } catch (URISyntaxException e) {
            // TODO('URI VO 만들어서 Exception 관리')
            throw new RuntimeException(e);
        }
    }

}
