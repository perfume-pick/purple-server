package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URISyntaxException;

public interface SocialLoginTryUseCase {

    Result invoke(
        SocialLoginProvider socialLoginProvider,
        String frontUrl
    ) throws URISyntaxException;

    record Result(String socialLoginUrl) {}

}
