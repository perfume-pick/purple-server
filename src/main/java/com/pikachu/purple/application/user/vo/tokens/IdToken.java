package com.pikachu.purple.application.user.vo.tokens;

import lombok.Getter;

@Getter
public class IdToken {

    private String email;

    public IdToken(String email) {
        this.email = email;
    }

}
