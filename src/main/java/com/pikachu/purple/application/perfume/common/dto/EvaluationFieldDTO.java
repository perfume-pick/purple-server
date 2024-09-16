package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;

public record EvaluationFieldDTO(
    String fieldCode,
    String fieldName,
    List<EvaluationOptionDTO> mostVotedOptions
) {

    public static EvaluationFieldDTO of(
        EvaluationFieldType fieldType,
        List<EvaluationOptionDTO> evaluationOptionDTOs
    ) {

        return new EvaluationFieldDTO(
            fieldType.getCode(),
            fieldType.getName(),
            evaluationOptionDTOs
        );
    }

}
