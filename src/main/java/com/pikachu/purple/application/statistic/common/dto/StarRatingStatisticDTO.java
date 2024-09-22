package com.pikachu.purple.application.statistic.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;

public record StarRatingStatisticDTO(
    int score,
    int votePercent
) {

    public static StarRatingStatisticDTO of(
        StarRatingStatistic starRatingStatistic,
        int totalVotes
    ) {
        return new StarRatingStatisticDTO(
            starRatingStatistic.getScore(),
            MathUtil.getPercentage(
                starRatingStatistic.getVotes(),
                totalVotes
            )
        );
    }

}
