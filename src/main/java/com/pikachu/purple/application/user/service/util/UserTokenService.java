package com.pikachu.purple.application.user.service.util;

import com.auth0.jwk.JwkException;
import com.pikachu.purple.application.user.vo.tokens.AccessToken;
import com.pikachu.purple.application.user.vo.tokens.IdToken;
import com.pikachu.purple.application.user.vo.tokens.JwtToken;
import com.pikachu.purple.application.user.vo.tokens.RefreshToken;
import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.MalformedURLException;

public interface UserTokenService {

    IdToken resolveIdToken(
        String idToken,
        SocialLoginProvider provider
    ) throws MalformedURLException, JwkException;

    AccessToken resolveAccessToken(String accessToken);

    RefreshToken resolveRefreshToken(String refreshToken);

    JwtToken resolveJwtToken(String jwtToken);

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    String generateJwtToken(
        User user,
        String accessToken,
        String refreshToken
    );

}
