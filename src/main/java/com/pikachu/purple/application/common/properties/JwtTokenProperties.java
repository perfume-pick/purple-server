package com.pikachu.purple.application.common.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(value = "auth.jwt")
public class JwtTokenProperties {

    private final String issuer;
    private final TokenProperties access;
    private final TokenProperties refresh;
    private final TokenProperties jwt;

    public record TokenProperties(Long expireSeconds, String secret) {
    }

}
