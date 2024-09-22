package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import java.util.List;

public record GetFragranticaEvaluationResponse(
    List<FragranticaEvaluationFieldDTO> fragranticaEvaluation
) {}
