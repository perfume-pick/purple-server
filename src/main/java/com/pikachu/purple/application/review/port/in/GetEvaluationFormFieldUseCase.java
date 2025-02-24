package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public interface GetEvaluationFormFieldUseCase {

    Result invoke();

    record Result(
        List<EvaluationField<EvaluationOption>> evaluationFields,
        List<Mood> moods
    ) {}

}
