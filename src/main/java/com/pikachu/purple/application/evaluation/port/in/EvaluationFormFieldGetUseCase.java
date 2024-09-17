package com.pikachu.purple.application.evaluation.port.in;

import com.pikachu.purple.application.perfume.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.review.Mood;
import java.util.List;

public interface EvaluationFormFieldGetUseCase {

    Result invoke();

    record Result(
        List<EvaluationFieldDTO> evaluationFieldDTOs,
        List<Mood> moods
    ) {}

}
