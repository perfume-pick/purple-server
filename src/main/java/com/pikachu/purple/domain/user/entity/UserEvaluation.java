package com.pikachu.purple.domain.user.entity;

import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOption;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvaluation {

    private Long userEvaluationId;
    private Long userId;
    private Long perfumeId;
    private EvaluationField field;
    private EvaluationOption option;

    @Builder
    public UserEvaluation(
        Long userEvaluationId,
        Long userId,
        Long perfumeId,
        EvaluationField field,
        EvaluationOption option
    ) {
        this.userEvaluationId = userEvaluationId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.field = field;
        this.option = option;
    }

}
