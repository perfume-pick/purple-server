package com.pikachu.purple.application.perfume.port.in.fragranticaevaluation;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import java.util.List;

public interface GetFragranticaEvaluationUseCase {

    Result findAll(Long perfumeId);

    record Result(List<FragranticaEvaluationFieldDTO> fragranticaEvaluation) {}

}
