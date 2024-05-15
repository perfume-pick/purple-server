package com.pikachu.purple.application.user.port.in;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public interface SocialLoginUseCase {

    Result invoke(SocialLoginUseCase.Command command)
        throws MalformedURLException, JwkException, URISyntaxException;

    record Command(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode
    ) {
    }

    record Result(
        URI socialLoginSuccessUrl
    ) {
    }
}
