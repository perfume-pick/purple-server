package com.pikachu.purple.bootstrap.common.security;

import com.pikachu.purple.bootstrap.common.vo.UserAuthentication;
import com.pikachu.purple.support.security.auth.SecurityContext;

public class UserSecurityContext implements SecurityContext<UserAuthentication> {

    private UserAuthentication authentication;

    public UserSecurityContext(UserAuthentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public UserAuthentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(UserAuthentication authentication) {
        this.authentication = authentication;
    }

}

