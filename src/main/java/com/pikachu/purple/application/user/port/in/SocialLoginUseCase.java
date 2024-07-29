package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface SocialLoginUseCase {

    Result invoke(SocialLoginUseCase.Command command);

    record Command(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode
    ) {

    }

    record Result(String jwtToken) {
    }
}
