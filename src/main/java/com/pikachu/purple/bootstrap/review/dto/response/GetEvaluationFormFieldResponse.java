package com.pikachu.purple.bootstrap.review.dto.response;

import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionDTO;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public record GetEvaluationFormFieldResponse(
    List<EvaluationFieldDTO<EvaluationOptionDTO>> evaluationFields,
    List<Mood> moods
) {}
