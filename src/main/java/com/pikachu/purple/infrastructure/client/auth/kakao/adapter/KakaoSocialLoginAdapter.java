package com.pikachu.purple.infrastructure.client.auth.kakao.adapter;

import com.pikachu.purple.application.user.port.out.SocialLoginPort;
import com.pikachu.purple.application.user.vo.SocialLoginTokenRequest;
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
        return kakaoSocialLoginClient.getToken(
            socialLoginTokenRequest.grantType(),
            socialLoginTokenRequest.clientId(),
            socialLoginTokenRequest.redirectUri(),
            socialLoginTokenRequest.authorizationCode()
        );
    }

}
