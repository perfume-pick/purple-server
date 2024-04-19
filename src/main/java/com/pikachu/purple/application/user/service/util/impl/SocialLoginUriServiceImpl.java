package com.pikachu.purple.application.user.service.util.impl;

import com.pikachu.purple.application.user.service.util.SocialLoginUriService;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginUriStrategy;
import com.pikachu.purple.application.user.service.util.strategy.SocialLoginUriStrategyFactory;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginUriServiceImpl implements SocialLoginUriService {

    private static final String ENCODED_QUERY_PARAMETER_DELIMETER = "&";
    private final SocialLoginUriStrategyFactory socialLoginUriStrategyFactory;

    @Override
    public URI createUri(SocialLoginProvider socialLoginProvider) {
        SocialLoginUriStrategy socialLoginUriStrategy = socialLoginUriStrategyFactory.getStrategy(
            socialLoginProvider);

        URI uri = socialLoginUriStrategy.getUri();

        UUID stateCode = generateState();

        try {
            return addState(uri, stateCode);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private UUID generateState() {
        return UUID.randomUUID();
    }

    private URI addState(
        URI uri,
        UUID stateCode
    ) throws URISyntaxException {
        return new URI(uri.toString() + ENCODED_QUERY_PARAMETER_DELIMETER + "state=" + stateCode);
    }

}
