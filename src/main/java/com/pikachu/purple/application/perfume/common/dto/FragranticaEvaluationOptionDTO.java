package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record FragranticaEvaluationOptionDTO(
    String optionCode,
    String optionName,
    int votePercent
) {

    public static FragranticaEvaluationOptionDTO of(
        EvaluationOptionType option,
        int votes,
        int totalVotesByField
    ) {

        return new FragranticaEvaluationOptionDTO(
            option.getCode(),
            option.getName(),
            MathUtil.getPercentage(
                votes,
                totalVotesByField
            )
        );
    }

}
