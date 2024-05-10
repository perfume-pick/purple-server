package com.pikachu.purple.application.user.port.in;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.MalformedURLException;

public interface SocialLoginUseCase {

    Result invoke(SocialLoginUseCase.Command command) throws MalformedURLException, JwkException;

    record Command(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode
    ) {
    }

    record Result(
        Url socialLoginSuccessUrl
    ) {
    }
}
