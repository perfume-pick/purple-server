package com.pikachu.purple.infrastructure.client.auth.kakao.util;

import com.pikachu.purple.domain.user.vo.SocialLogoutId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "kakao-logout-request",
    url = "https://kapi.kakao.com"
)
public interface KakaoSocialLogoutClient {

    @PostMapping(value = "/v1/user/logout", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    SocialLogoutId logout(@RequestHeader("Authorization") String authorizationHeader);

}
