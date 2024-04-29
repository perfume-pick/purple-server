package com.pikachu.purple.application.user.service.util.strategy;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialLoginStrategyFactoryImpl implements SocialLoginStrategyFactory {

    private final ApplicationContext applicationContext;

    @Override
    public SocialLoginStrategy getStrategy(SocialLoginProvider socialLoginProvider) {
        return switch (socialLoginProvider) {
            case KAKAO -> applicationContext.getBean(KakaoSocialLoginStrategy.class);
        };
    }

}
