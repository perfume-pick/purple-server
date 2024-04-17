package com.pikachu.purple.application.user.service.util.strategy;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface SocialLoginUriStrategyFactory {

    SocialLoginUriStrategy getStrategy(SocialLoginProvider socialLoginProvider);

}
