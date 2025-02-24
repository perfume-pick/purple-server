package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record EvaluationOption(
    String optionCode,
    String optionName
) {

    public static EvaluationOption from(
        EvaluationOptionType optionType
    ) {
        return new EvaluationOption(
            optionType.getCode(),
            optionType.getName()
        );
    }

}