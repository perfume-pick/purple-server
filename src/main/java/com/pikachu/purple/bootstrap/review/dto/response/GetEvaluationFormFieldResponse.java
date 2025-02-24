package com.pikachu.purple.bootstrap.review.dto.response;

import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase.Result;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;
import lombok.Getter;

@Getter
public class GetEvaluationFormFieldResponse {

    private final List<EvaluationField<EvaluationOption>> evaluationFields;
    private final List<Mood> moods;

    private GetEvaluationFormFieldResponse(
        List<EvaluationField<EvaluationOption>> evaluationFields,
        List<Mood> moods
    ) {
        this.evaluationFields = evaluationFields;
        this.moods = moods;
    }

    public static GetEvaluationFormFieldResponse of(Result result) {
        return new GetEvaluationFormFieldResponse(
            result.evaluationFields(),
            result.moods()
        );
    }

}
