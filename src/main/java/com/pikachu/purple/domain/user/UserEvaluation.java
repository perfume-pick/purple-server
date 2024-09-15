package com.pikachu.purple.domain.user;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
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
    private EvaluationFieldType field;
    private EvaluationOptionType option;

    @Builder
    public UserEvaluation(
        Long userEvaluationId,
        Long userId,
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        this.userEvaluationId = userEvaluationId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.field = field;
        this.option = option;
    }

}
