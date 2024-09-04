package com.pikachu.purple.domain.evaluation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationFieldOption {

    private Long evaluationFieldOptionId;
    private String fieldCode;
    private String optionCode;

    @Builder
    public EvaluationFieldOption(
        Long evaluationFieldOptionId,
        String fieldCode,
        String optionCode
    ) {
        this.evaluationFieldOptionId = evaluationFieldOptionId;
        this.fieldCode = fieldCode;
        this.optionCode = optionCode;
    }

}
