package com.pikachu.purple.infrastructure.client.auth.kakao.adapter;

import com.pikachu.purple.application.user.port.out.SocialLoginPort;
import com.pikachu.purple.application.user.vo.SocialLoginTokenRequest;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import com.pikachu.purple.infrastructure.client.auth.kakao.util.KakaoSocialLoginClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoSocialLoginAdapter implements SocialLoginPort {

    private final KakaoSocialLoginClient kakaoSocialLoginClient;

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

}
