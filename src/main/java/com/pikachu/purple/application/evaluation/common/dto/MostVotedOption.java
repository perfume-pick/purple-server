package com.pikachu.purple.application.evaluation.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;

public record MostVotedOption(
    String optionCode,
    String optionName,
    int votePercent
) {

    public static MostVotedOption of(
        FragranticaEvaluation fragranticaEvaluation,
        int totalVotesByField
    ) {
        return new MostVotedOption(
            fragranticaEvaluation.getOption().getCode(),
            fragranticaEvaluation.getOption().getName(),
            MathUtil.getPercentage(fragranticaEvaluation.getVotes(), totalVotesByField)
        );
    }

}
