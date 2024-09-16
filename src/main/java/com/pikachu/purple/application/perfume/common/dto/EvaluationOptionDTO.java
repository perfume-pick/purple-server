package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;

public record EvaluationOptionDTO(
    String optionCode,
    String optionName,
    int votePercent
) {

    public static EvaluationOptionDTO of(
        EvaluationOptionStatistic evaluationOptionStatistic,
        int totalVotesByField
    ) {
        return new EvaluationOptionDTO(
            evaluationOptionStatistic.getType().getCode(),
            evaluationOptionStatistic.getType().getName(),
            MathUtil.getPercentage(
                evaluationOptionStatistic.getVotes(),
                totalVotesByField
            )
        );
    }

}