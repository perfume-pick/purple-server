package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.accord.Accord;
import lombok.Getter;

@Getter
public class PerfumeAccord extends Accord {

    private final int value;

    public PerfumeAccord(
        String name,
        String koreanName,
        int value
    ) {
        super(name, koreanName);
        this.value = value;
    }

}
