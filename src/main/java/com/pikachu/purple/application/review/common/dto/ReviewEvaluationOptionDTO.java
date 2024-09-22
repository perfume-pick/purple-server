package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record ReviewEvaluationOptionDTO(String optionName) {

    public static ReviewEvaluationOptionDTO of(EvaluationOptionType type) {
        return new ReviewEvaluationOptionDTO(type.getName());
    }

}
