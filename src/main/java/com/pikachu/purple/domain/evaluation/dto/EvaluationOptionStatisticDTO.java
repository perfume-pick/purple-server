package com.pikachu.purple.domain.evaluation.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record EvaluationOptionStatisticDTO(
    int order,
    String optionCode,
    String optionName,
    int votePercent
) {

    public static EvaluationOptionStatisticDTO of(
        int order,
        EvaluationOptionType option,
        int votes,
        int totalVotesByField
    ) {
        return new EvaluationOptionStatisticDTO(
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