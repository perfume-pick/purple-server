package com.pikachu.purple.domain.review;

import lombok.Getter;

@Getter
public class Mood {

    private final String name;

    public Mood(String name) {
        this.name = name;
    }

}
