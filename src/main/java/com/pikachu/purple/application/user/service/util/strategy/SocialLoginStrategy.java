package com.pikachu.purple.application.user.service.util.strategy;

import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.net.URI;
import java.net.URISyntaxException;

public interface SocialLoginStrategy {

    URI getUrl(String frontUrl) throws URISyntaxException;

    SocialLoginToken getToken(String authorizationCode, String frontUrl);

    IdToken resolveIdToken(String idToken);

    Long logout(String accessToken, String refreshToken);

}
