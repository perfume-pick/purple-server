package com.pikachu.purple.application.user.service.util.impl;

import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginStrategy;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginStrategyFactory;
import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {

    private static final String QUERY_PARAMETER_DELIMITER = "&";
    private final SocialLoginStrategyFactory socialLoginStrategyFactory;

    @Override
    public Url createUri(SocialLoginProvider socialLoginProvider) {
        SocialLoginStrategy socialLoginUriStrategy = socialLoginStrategyFactory.getStrategy(
            socialLoginProvider);

        Url url = socialLoginUriStrategy.getUrl();

        UUID stateCode = generateState();

        return addState(url, stateCode);
    }

    @Override
    public SocialLoginToken getToken(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode
    ) {
        SocialLoginStrategy socialLoginUriStrategy = socialLoginStrategyFactory.getStrategy(
            socialLoginProvider);

        return socialLoginUriStrategy.getToken(authorizationCode);
    }

    private UUID generateState() {
        return UUID.randomUUID();
    }

    private Url addState(
        Url url,
        UUID stateCode
    ) {
        return new Url(url.toString() + QUERY_PARAMETER_DELIMITER + "state=" + stateCode);
    }

}
