package com.pikachu.purple.application.evaluation.port.in;

import com.pikachu.purple.application.evaluation.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import java.util.List;

public interface EvaluationFieldAndEvaluationMoodGetUseCase {

    Result invoke();

    record Result(
        List<EvaluationFieldDTO> evaluationFieldDTOS,
        List<EvaluationMood> evaluationMoods
    ) {

    }

}