package com.pikachu.purple.application.user.service.util;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.net.URI;
import java.net.URISyntaxException;

public interface SocialLoginService {

    URI createUri(
        SocialLoginProvider socialLoginProvider,
        String frontUrl
    ) throws URISyntaxException;

    SocialLoginToken getToken(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode,
        String frontUrl
    );

}
