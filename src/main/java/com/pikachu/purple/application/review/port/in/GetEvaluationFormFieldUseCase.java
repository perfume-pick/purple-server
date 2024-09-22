package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionDTO;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public interface GetEvaluationFormFieldUseCase {

    Result invoke();

    record Result(
        List<EvaluationFieldDTO<EvaluationOptionDTO>> evaluationFieldDTOs,
        List<Mood> moods
    ) {}

}
