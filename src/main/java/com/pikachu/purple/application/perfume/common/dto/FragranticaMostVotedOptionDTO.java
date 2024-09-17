package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;

public record FragranticaMostVotedOptionDTO(
    String optionCode,
    String optionName,
    int votePercent
) {

    public static FragranticaMostVotedOptionDTO of(
        EvaluationOptionStatistic evaluationOptionStatistic,
        int totalVotesByField
    ) {
        return new FragranticaMostVotedOptionDTO(
            evaluationOptionStatistic.getType().getCode(),
            evaluationOptionStatistic.getType().getName(),
            MathUtil.getPercentage(
                evaluationOptionStatistic.getVotes(),
                totalVotesByField
            )
        );
    }

}