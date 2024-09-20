package com.pikachu.purple.domain.evaluation.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;

public record EvaluationOptionStatisticDTO(
    int order,
    String optionCode,
    String optionName,
    int votePercent
) {

    public static EvaluationOptionStatisticDTO of(
        int order,
        EvaluationOptionStatistic evaluationOptionStatistic,
        int totalVotesByField
    ) {
        return new EvaluationOptionStatisticDTO(
            order,
            evaluationOptionStatistic.getType().getCode(),
            evaluationOptionStatistic.getType().getName(),
            MathUtil.getPercentage(
                evaluationOptionStatistic.getVotes(),
                totalVotesByField
            )
        );
    }

}