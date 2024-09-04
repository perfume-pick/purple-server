package com.pikachu.purple.application.evaluation.common.dto;

import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import java.util.List;

public record FragranticaEvaluationDto(
    String fieldCode,
    String fieldName,
    List<MostVotedOption> mostVotedOptions
) {

    public static FragranticaEvaluationDto of(
        EvaluationField evaluationField,
        List<MostVotedOption> mostVotedOptions
    ) {
        return new FragranticaEvaluationDto(
            evaluationField.getCode(),
            evaluationField.getName(),
            mostVotedOptions
        );
    }

}
