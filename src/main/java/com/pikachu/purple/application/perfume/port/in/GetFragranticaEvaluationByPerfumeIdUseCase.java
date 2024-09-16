package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.perfume.FragranticaEvaluation;
import java.util.List;

public interface GetFragranticaEvaluationByPerfumeIdUseCase {

    GetFragranticaEvaluationByPerfumeIdUseCase.Result invoke(
        GetFragranticaEvaluationByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {}

    record Result(List<EvaluationFieldDTO> fragranticaEvaluation) {}

}
