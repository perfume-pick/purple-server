package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.evaluation.common.dto.FragranticaEvaluationDTO;
import java.util.List;

public record GetFragranticaEvaluationsResponse(
    List<FragranticaEvaluationDTO> fragranticaEvaluations
) {

}
