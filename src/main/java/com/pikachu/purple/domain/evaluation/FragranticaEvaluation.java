package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOption;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FragranticaEvaluation {

    private Long fragranticaEvaluationId;
    private Long perfumeId;
    private EvaluationField field;
    private EvaluationOption option;
    private int votes;

    @Builder
    public FragranticaEvaluation(
            Long fragranticaEvaluationId,
            Long perfumeId,
            EvaluationField field,
            EvaluationOption option,
            int votes
    ) {
        this.fragranticaEvaluationId = fragranticaEvaluationId;
        this.perfumeId = perfumeId;
        this.field = field;
        this.option = option;
        this.votes = votes;
    }

}
