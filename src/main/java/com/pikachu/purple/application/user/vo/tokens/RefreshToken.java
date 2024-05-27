package com.pikachu.purple.application.user.vo.tokens;

import lombok.Getter;

@Getter
public class RefreshToken {

    private final Long userId;

    public RefreshToken(Long userId) {
        this.userId = userId;
    }

}
