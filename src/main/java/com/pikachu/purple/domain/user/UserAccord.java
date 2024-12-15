package com.pikachu.purple.domain.user;

import com.pikachu.purple.domain.accord.Accord;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserAccord extends Accord {

    private final double score;

    @Setter
    private User user;

    public UserAccord(
        String name,
        String koreanName,
        double score
    ) {
        super(name, koreanName);
        this.score = score;
    }

}
