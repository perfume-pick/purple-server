package com.pikachu.purple.application.perfume.port.in.fragranticaevaluation;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import java.util.List;

public interface GetFragranticaEvaluationByPerfumeIdUseCase {

    Result invoke(Long perfumeId);

    record Result(List<FragranticaEvaluationFieldDTO> fragranticaEvaluation) {}

}
