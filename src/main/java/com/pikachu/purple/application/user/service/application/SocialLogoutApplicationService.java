package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.SocialLogoutUseCase;
import com.pikachu.purple.application.user.service.util.OAuthTokenService;
import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.application.user.service.util.UserTokenService;
import com.pikachu.purple.infrastructure.redis.user.entity.OAuthTokenRedisHash;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialLogoutApplicationService implements SocialLogoutUseCase {

    private final UserTokenService userTokenService;
    private final OAuthTokenService oAuthTokenService;
    private final SocialLoginService socialLoginService;

    @Value("${uri.client}")
    private String clientUrl;

    @Transactional
    @Override
    public String invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        userTokenService.deleteAllToken(userId);

        OAuthTokenRedisHash oAuthTokenRedisHash = oAuthTokenService.find(userId);

        Long logoutId = socialLoginService.logout(
            oAuthTokenRedisHash.getAccessToken(),
            oAuthTokenRedisHash.getRefreshToken()
        );

        log.info("logoutId = {}", logoutId);

        return clientUrl;
    }

}
