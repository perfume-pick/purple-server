package com.pikachu.purple.application.user.vo;

import lombok.Getter;

@Getter
public class Nickname {

    private final String value;

    public Nickname(int userTotalCount) {
        this.value = create(userTotalCount);
    }

    private String create(int userTotalCount) {
        return String.format("퍼픽%03d", userTotalCount + 1);
    }
}
