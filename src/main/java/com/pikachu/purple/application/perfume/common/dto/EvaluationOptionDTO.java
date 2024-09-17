package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;

public record EvaluationOptionDTO(
    String optionCode,
    String optionName
) {

    public static EvaluationOptionDTO from(
        EvaluationOptionType optionType
    ) {
        return new EvaluationOptionDTO(
            optionType.getCode(),
            optionType.getName()
        );
    }

}