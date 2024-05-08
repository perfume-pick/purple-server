package com.pikachu.purple.application.user.vo.tokens;

public class AccessToken {
    private Long userId;

    private String email;

    public AccessToken(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

}
