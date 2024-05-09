package com.pikachu.purple.application.user.service.util.strategy;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.net.MalformedURLException;

public interface SocialLoginStrategy {

    Url getUrl();

    SocialLoginToken getToken(String authorizationCode);

    IdToken resolveIdToken(String idToken) throws MalformedURLException, JwkException;

}
