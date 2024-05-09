package com.pikachu.purple.infrastructure.client.auth.kakao.util;

import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "kakao-token-request",
    url = "https://kauth.kakao.com/oauth/token"
)
public interface KakaoSocialLoginClient {

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    SocialLoginToken getToken(
        @RequestParam(value = "grant_type") String grantType,
        @RequestParam(value = "client_id") String clientId,
        @RequestParam(value = "redirect_uri") String redirectUri,
        @RequestParam(value = "code") String code
    );

}
