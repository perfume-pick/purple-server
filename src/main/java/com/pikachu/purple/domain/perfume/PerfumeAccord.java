package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.accord.enums.Accord;
import lombok.Getter;

@Getter
public class PerfumeAccord {

    private final Accord accord;
    private final int value;

    public PerfumeAccord(
        Accord accord,
        int value
    ) {
        this.accord = accord;
        this.value = value;
    }

}
