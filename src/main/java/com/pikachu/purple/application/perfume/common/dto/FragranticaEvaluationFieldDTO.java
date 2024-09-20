package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;

public record FragranticaEvaluationFieldDTO(
    String fieldCode,
    String fieldName,
    List<FragranticaEvaluationOptionDTO> mostVotedOptions
) {

    public static FragranticaEvaluationFieldDTO of(
        EvaluationFieldType fieldType,
        List<FragranticaEvaluationOptionDTO> mostVotedOptions
    ) {

        return new FragranticaEvaluationFieldDTO(
            fieldType.getCode(),
            fieldType.getName(),
            mostVotedOptions
        );
    }

}
