package com.pikachu.purple.domain.accord;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Accord {

    private final String name;

    public Accord(String name) {
        this.name = name;
    }

}
