package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import java.util.List;

public interface GetFragranticaEvaluationByPerfumeIdUseCase {

    Result invoke(Command command);

    record Command(Long perfumeId) {}

    record Result(List<FragranticaEvaluationFieldDTO> fragranticaEvaluation) {}

}
