package com.pikachu.purple.domain.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRating {

    private int score;

    public void updateScore(int score) {
        this.score = score;
    }

}
