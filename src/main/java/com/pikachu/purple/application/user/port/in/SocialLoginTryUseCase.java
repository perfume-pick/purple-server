package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URISyntaxException;

public interface SocialLoginTryUseCase {

    Result invoke(Command command) throws URISyntaxException;

    record Command(
        SocialLoginProvider socialLoginProvider,
        String frontUrl
    ) {}

    record Result(
        String socialLoginUrl
    ) {}

}
