package com.pikachu.purple.application.evaluation.port.in;

import com.pikachu.purple.application.evaluation.common.dto.FragranticaEvaluationDTO;
import java.util.List;

public interface FragranticaEvaluationGetByPerfumeIdUseCase {

    FragranticaEvaluationGetByPerfumeIdUseCase.Result invoke(
        FragranticaEvaluationGetByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {

    }

    record Result(List<FragranticaEvaluationDTO> fragranticaEvaluations) {

    }

}
