package com.pikachu.purple.bootstrap.common.vo;

import com.pikachu.purple.application.user.vo.tokens.AccessToken;
import com.pikachu.purple.support.security.auth.Authentication;

public record UserAuthentication(
    Long userId,
    String email
) implements Authentication {

    public static UserAuthentication from(AccessToken accessToken) {
        return new UserAuthentication(
            accessToken.getUserId(),
            accessToken.getEmail()
        );
    }

}
