package com.pikachu.purple.domain.evaluation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FragranticaEvaluation {

    private Long fragranticaEvaluationId;
    private Long perfumeId;
    private String fieldCode;
    private String fieldName;
    private String optionCode;
    private String optionName;
    private Integer votes;

    @Builder
    public FragranticaEvaluation(
            Long fragranticaEvaluationId,
            Long perfumeId,
            String fieldCode,
            String fieldName,
            String optionCode,
            String optionName,
            Integer votes
    ) {
        this.fragranticaEvaluationId = fragranticaEvaluationId;
        this.perfumeId = perfumeId;
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
        this.optionCode = optionCode;
        this.optionName = optionName;
        this.votes = votes;
    }
}
