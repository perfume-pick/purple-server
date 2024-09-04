package com.pikachu.purple.application.evaluation.port.out;

import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;
import java.util.List;

public interface FragranticaEvaluationRepository {

    List<FragranticaEvaluation> findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
        Long perfumeId,
        String fieldCode,
        int maxSize
    );

}
