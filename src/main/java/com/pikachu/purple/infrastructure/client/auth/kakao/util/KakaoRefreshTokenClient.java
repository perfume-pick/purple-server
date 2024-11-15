package com.pikachu.purple.infrastructure.client.auth.kakao.util;

import com.pikachu.purple.domain.user.vo.SocialRefreshToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "kakao-refresh-token-request",
    url = "https://kapi.kakao.com"
)
public interface KakaoRefreshTokenClient {

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    SocialRefreshToken refreshToken(
        @RequestParam(value = "grant_type") String grantType,
        @RequestParam(value = "client_id") String clientId,
        @RequestParam(value = "refresh_token") String refreshToken
    );

}
