package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import java.util.List;

public interface GetFragranticaEvaluationByPerfumeIdUseCase {

    GetFragranticaEvaluationByPerfumeIdUseCase.Result invoke(
        GetFragranticaEvaluationByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {}

    record Result(List<FragranticaEvaluationFieldDTO> fragranticaEvaluation) {}

}
