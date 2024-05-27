package com.pikachu.purple.application.user.service.util.strategy;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public interface SocialLoginStrategy {

    URI getUrl() throws URISyntaxException;

    SocialLoginToken getToken(String authorizationCode);

    IdToken resolveIdToken(String idToken) throws MalformedURLException, JwkException;

}
