package com.pikachu.purple.application.perfume.port.in.fragranticaevaluation;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import com.pikachu.purple.application.util.IdUtil;
import java.util.List;

public interface GetFragranticaEvaluationByPerfumeIdUseCase {

    Result invoke(Command command);

    record Command(Long perfumeId) {

        public Command(String perfumeId) {
            this(IdUtil.from(perfumeId));
        }

    }

    record Result(List<FragranticaEvaluationFieldDTO> fragranticaEvaluation) {}

}
