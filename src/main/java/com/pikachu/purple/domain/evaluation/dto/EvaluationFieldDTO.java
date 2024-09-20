package com.pikachu.purple.domain.evaluation.dto;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;

public record EvaluationFieldDTO<T>(
    String fieldCode,
    String fieldName,
    List<T> evaluationOptions
) {

    public static <T>EvaluationFieldDTO<T> of(
        EvaluationFieldType fieldType,
        List<T> evaluationOptionDTOs
    ) {

        return new EvaluationFieldDTO<>(
            fieldType.getCode(),
            fieldType.getName(),
            evaluationOptionDTOs
        );
    }

}
