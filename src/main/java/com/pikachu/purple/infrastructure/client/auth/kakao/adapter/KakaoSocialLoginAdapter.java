package com.pikachu.purple.infrastructure.client.auth.kakao.adapter;

import com.pikachu.purple.application.user.port.out.SocialLoginPort;
import com.pikachu.purple.application.user.vo.SocialLoginTokenRequest;
import com.pikachu.purple.application.user.vo.SocialRefreshTokenRequest;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import com.pikachu.purple.infrastructure.client.auth.kakao.util.KakaoRefreshTokenClient;
import com.pikachu.purple.infrastructure.client.auth.kakao.util.KakaoSocialLoginClient;
import com.pikachu.purple.infrastructure.client.auth.kakao.util.KakaoSocialLogoutClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KakaoSocialLoginAdapter implements SocialLoginPort {

    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    private final KakaoSocialLoginClient kakaoSocialLoginClient;
    private final KakaoSocialLogoutClient kakaoSocialLogoutClient;
    private final KakaoRefreshTokenClient kakaoRefreshTokenClient;

    @Override
    public SocialLoginToken getToken(SocialLoginTokenRequest socialLoginTokenRequest) {
        try {
            return kakaoSocialLoginClient.getToken(
                socialLoginTokenRequest.grantType(),
                socialLoginTokenRequest.clientId(),
                socialLoginTokenRequest.redirectUri(),
                socialLoginTokenRequest.authorizationCode()
            );
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.NOT_VALID_LOGIN_ID_TOKEN);
        }

    }

    @Override
    public Long logout(String accessToken, SocialRefreshTokenRequest socialRefreshTokenRequest) {
        try {
            return kakaoSocialLogoutClient.logout(AUTHORIZATION_PREFIX + accessToken).userId();
        } catch (FeignException.Unauthorized e) {
            logout(
                kakaoRefreshTokenClient.refreshToken(
                socialRefreshTokenRequest.grantType(),
                socialRefreshTokenRequest.clientId(),
                socialRefreshTokenRequest.refreshToken()
            ).accessToken(), null);
        }
        return null;
    }

}
