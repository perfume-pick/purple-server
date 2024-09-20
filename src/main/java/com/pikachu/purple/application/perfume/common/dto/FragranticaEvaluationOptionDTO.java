package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;

public record FragranticaEvaluationOptionDTO(
    String optionCode,
    String optionName,
    int votePercent
) {

    public static FragranticaEvaluationOptionDTO of(
        EvaluationOptionStatistic evaluationOptionStatistic,
        int totalVotesByField
    ) {

        return new FragranticaEvaluationOptionDTO(
            evaluationOptionStatistic.getType().getCode(),
            evaluationOptionStatistic.getType().getName(),
            MathUtil.getPercentage(
                evaluationOptionStatistic.getVotes(),
                totalVotesByField
            )
        );
    }

}
