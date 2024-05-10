package com.pikachu.purple.application.user.vo;

import lombok.Getter;

@Getter
public class Nickname {

    private static final String NICKNAME_FORMAT = "퍼픽%03d";
    private final String value;

    public Nickname(int countTotalUsers){
        this.value = createRandom(countTotalUsers);
    }

    private String createRandom(int countTotalUsers){
        return String.format(NICKNAME_FORMAT, countTotalUsers+1);
    }

}
