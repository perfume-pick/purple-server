package com.pikachu.purple.application.user.vo;

import lombok.Getter;

@Getter
public class Nickname {

    private final String value;

    public Nickname(int countTotalUsers){
        this.value = createRandom(countTotalUsers);
    }

    private String createRandom(int countTotalUsers){
        return String.format("퍼픽%03d", countTotalUsers+1);
    }

}
