package com.pikachu.purple.application.evaluation.service.domain;

import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;
import java.util.List;

public interface FragranticaEvaluationDomainService {

    List<FragranticaEvaluation> findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
        Long perfumeId,
        String fieldCode
    );

}
