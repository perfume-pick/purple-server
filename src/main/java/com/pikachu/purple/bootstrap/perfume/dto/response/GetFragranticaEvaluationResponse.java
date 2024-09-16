package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.EvaluationFieldDTO;
import java.util.List;

public record GetFragranticaEvaluationResponse(
    List<EvaluationFieldDTO> fragranticaEvaluation
) {}
