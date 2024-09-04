package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.domain.evaluation.enums.EvaluationCodeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationCode {

    private String code;
    private String name;
    private EvaluationCodeType type;

    @Builder
    public EvaluationCode(
        String code,
        String name,
        EvaluationCodeType type
    ) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

}
