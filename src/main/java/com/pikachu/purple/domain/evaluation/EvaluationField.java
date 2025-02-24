package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;

public record EvaluationField<T>(
    String fieldCode,
    String fieldName,
    List<T> evaluationOptions
) {

    public static <T> EvaluationField<T> of(
        EvaluationFieldType fieldType,
        List<T> evaluationOptionDTOs
    ) {

        return new EvaluationField<>(
            fieldType.getCode(),
            fieldType.getName(),
            evaluationOptionDTOs
        );
    }

}
