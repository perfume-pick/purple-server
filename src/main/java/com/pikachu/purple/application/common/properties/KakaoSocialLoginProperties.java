package com.pikachu.purple.application.common.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(value = "auth.kakao")
public class KakaoSocialLoginProperties {
    private final String clientId;
    private final String responseType;
    private final String authorizeUri;
    private final String tokenRequestUri;
    private final String redirectUri;
    private final String jwksUri;
    private final String loginSuccessUri;
}
