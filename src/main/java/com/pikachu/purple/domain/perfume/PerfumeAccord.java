package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.accord.Accord;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeAccord extends Accord {

    private int value;

}
