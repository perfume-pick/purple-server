package com.pikachu.purple.domain.evaluation.dto;

import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record EvaluationOptionDTO(
    String optionCode,
    String optionName
) {

    public static EvaluationOptionDTO from(
        EvaluationOptionType optionType
    ) {
        return new EvaluationOptionDTO(
            optionType.getCode(),
            optionType.getName()
        );
    }

}