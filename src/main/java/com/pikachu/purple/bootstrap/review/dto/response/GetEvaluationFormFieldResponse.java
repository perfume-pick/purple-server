package com.pikachu.purple.bootstrap.review.dto.response;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public record GetEvaluationFormFieldResponse(
    List<EvaluationField<EvaluationOption>> evaluationFields,
    List<Mood> moods
) {}
