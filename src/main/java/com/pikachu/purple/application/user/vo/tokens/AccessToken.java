package com.pikachu.purple.application.user.vo.tokens;

import lombok.Getter;

@Getter
public class AccessToken {
    private Long userId;

    private String email;

    public AccessToken(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

}
