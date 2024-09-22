package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;

public record ReviewEvaluationFieldDTO(
    String fieldName,
    List<ReviewEvaluationOptionDTO> options
) {

    public static ReviewEvaluationFieldDTO of(
        EvaluationFieldType fieldType,
        List<ReviewEvaluationOptionDTO> options) {
        return new ReviewEvaluationFieldDTO(
            fieldType.getName(),
            options
        );
    }

}
