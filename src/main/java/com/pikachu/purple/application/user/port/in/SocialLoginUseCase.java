package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface SocialLoginUseCase {

    Result invoke(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode,
        String frontUrl
    );

    record Result(
        String jwtToken,
        boolean isSignUp,
        String nickname,
        String imageUrl
    ) {}

}
