package com.pikachu.purple.application.user.service.util.strategy;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface SocialLoginStrategyFactory {

    SocialLoginStrategy getStrategy(SocialLoginProvider socialLoginProvider);

}
