package com.pikachu.purple.application.user.service.util.impl;

import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginStrategy;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginStrategyFactory;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {

    private static final String QUERY_PARAMETER_DELIMITER = "&";
    private final SocialLoginStrategyFactory socialLoginStrategyFactory;

    @Override
    public URI createUri(
        SocialLoginProvider socialLoginProvider,
        String frontUrl
    ) throws URISyntaxException {
        SocialLoginStrategy socialLoginUriStrategy = socialLoginStrategyFactory.getStrategy(
            socialLoginProvider);

        URI uri = socialLoginUriStrategy.getUrl(frontUrl);

        UUID stateCode = generateState();

        return addState(uri, stateCode);
    }

    @Override
    public SocialLoginToken getToken(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode,
        String frontUrl
    ) {
        SocialLoginStrategy socialLoginUriStrategy = socialLoginStrategyFactory.getStrategy(
            socialLoginProvider);

        return socialLoginUriStrategy.getToken(
            authorizationCode,
            frontUrl
        );
    }

    @Override
    public Long logout(
        String accessToken,
        String refreshToken
    ) {
        SocialLoginStrategy socialLoginStrategy = socialLoginStrategyFactory.getStrategy(
            SocialLoginProvider.KAKAO
        );

        return socialLoginStrategy.logout(
            accessToken,
            refreshToken
        );
    }

    private UUID generateState() {
        return UUID.randomUUID();
    }

    private URI addState(
        URI uri,
        UUID stateCode
    ) throws URISyntaxException {
        return new URI(uri.toString() + QUERY_PARAMETER_DELIMITER + "state=" + stateCode);
    }

}
