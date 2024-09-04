package com.pikachu.purple.application.evaluation.common.dto;

public record EvaluationOptionDTO(
    String optionCode,
    String optionName
) {

    public static EvaluationOptionDTO of(
        String optionCode,
        String optionName
    ) {
        return new EvaluationOptionDTO(
            optionCode,
            optionName
        );
    }

}
