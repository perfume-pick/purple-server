package com.pikachu.purple.application.evaluation.common.dto;

import java.util.List;

public record EvaluationFieldDTO(
    String fieldCode,
    String fieldName,
    List<EvaluationOptionDTO> evaluationOptionDTOs
) {

    public static EvaluationFieldDTO of(
        String fieldCode,
        String fieldName,
        List<EvaluationOptionDTO> evaluationOptionDTOs
    ) {
        return new EvaluationFieldDTO(
            fieldCode,
            fieldName,
            evaluationOptionDTOs
        );
    }

}
