package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URISyntaxException;

public interface SocialLoginTryUseCase {

    Result invoke(SocialLoginTryUseCase.Command command) throws URISyntaxException;

    record Command(
        SocialLoginProvider socialLoginProvider
    ) {

    }

    record Result(
        String socialLoginUrl
    ) {

    }

}
