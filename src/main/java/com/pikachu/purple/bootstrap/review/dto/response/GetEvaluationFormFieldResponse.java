package com.pikachu.purple.bootstrap.review.dto.response;

import com.pikachu.purple.application.evaluation.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import java.util.List;

public record GetEvaluationFormFieldResponse(
    List<EvaluationFieldDTO> evaluationFieldDTOs,
    List<EvaluationMood> moods
) {

}
