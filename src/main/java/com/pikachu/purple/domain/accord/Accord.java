package com.pikachu.purple.domain.accord;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Accord {

    private final String name;
    private final String koreanName;

    public Accord(
        String name,
        String koreanName
    ) {
        this.name = name;
        this.koreanName = koreanName;
    }

}
