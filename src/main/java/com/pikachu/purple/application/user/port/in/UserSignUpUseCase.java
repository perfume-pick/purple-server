package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;

public interface UserSignUpUseCase {

    void invoke(
        String email,
        SocialLoginProvider socialLoginProvider
    );

}
