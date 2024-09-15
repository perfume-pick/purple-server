package com.pikachu.purple.domain.statistic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRatingStatistic {

    private int score;
    private int votes;

}
