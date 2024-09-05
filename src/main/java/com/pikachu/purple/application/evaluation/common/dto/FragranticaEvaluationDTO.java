package com.pikachu.purple.application.evaluation.common.dto;

import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import java.util.List;

public record FragranticaEvaluationDTO(
    String fieldCode,
    String fieldName,
    List<MostVotedOption> mostVotedOptions
) {

    public static FragranticaEvaluationDTO of(
        EvaluationField evaluationField,
        List<MostVotedOption> mostVotedOptions
    ) {
        return new FragranticaEvaluationDTO(
            evaluationField.getCode(),
            evaluationField.getName(),
            mostVotedOptions
        );
    }

}
