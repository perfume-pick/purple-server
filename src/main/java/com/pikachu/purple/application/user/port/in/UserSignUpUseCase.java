package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserSignUpUseCase {

    void invoke(Command command);

    record Command(
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
    }

}
