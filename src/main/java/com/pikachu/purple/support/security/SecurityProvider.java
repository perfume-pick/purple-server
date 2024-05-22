package com.pikachu.purple.support.security;

import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.bootstrap.common.vo.UserAuthentication;
import com.pikachu.purple.support.security.auth.Authentication;
import com.pikachu.purple.support.security.auth.SecurityContext;
import com.pikachu.purple.support.security.auth.SecurityContextHolder;

public class SecurityProvider {

    public static UserAuthentication getCurrentUserAuthentication() {
        SecurityContext<Authentication> authenticationContext = SecurityContextHolder.getContext();

        UserAuthentication userAuthentication = (UserAuthentication) authenticationContext.getAuthentication();

        if (userAuthentication == null) {
            throw new BusinessException(ErrorCode.USER_NOT_AUTHENTICATED);
        }

        return userAuthentication;
    }

}
