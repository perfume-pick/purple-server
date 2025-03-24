package com.pikachu.purple.domain.user;

import com.pikachu.purple.domain.accord.enums.Accord;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserAccord {

    private final Accord accord;
    private final double score;

    @Setter
    private User user;

    public UserAccord(
        Accord accord,
        double score
    ) {
        this.accord = accord;
        this.score = score;
    }

}
