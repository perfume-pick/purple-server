package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record EvaluationOptionStatistic(
    int order,
    String optionCode,
    String optionName,
    int votePercent
) {

    public static EvaluationOptionStatistic of(
        int order,
        EvaluationOptionType option,
        int votes,
        int totalVotesByField
    ) {
        return new EvaluationOptionStatistic(
            order,
            option.getCode(),
            option.getName(),
            MathUtil.getPercentage(
                votes,
                totalVotesByField
            )
        );
    }

}