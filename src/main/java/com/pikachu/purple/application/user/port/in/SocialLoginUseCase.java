package com.pikachu.purple.application.user.port.in;

public interface SocialLoginUseCase {

    Result invoke(SocialLoginUseCase.Command command);

    record Command(String authorizationCode) {}

    record Result(
        String jwtToken,
        boolean isSignUp,
        String nickname,
        String imageUrl
    ) {

    }

}
