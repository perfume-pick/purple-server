package com.pikachu.purple.domain.statistic;

import com.pikachu.purple.domain.perfume.Perfume;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRatingStatistic {

    private Perfume perfume;
    private int score;
    private int votes;

    public boolean isZero() {
        return this.votes == 0;
    }

}
