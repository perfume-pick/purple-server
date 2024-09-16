package com.pikachu.purple.application.evaluation.service.domain;

import com.pikachu.purple.domain.perfume.FragranticaEvaluation;

public interface FragranticaEvaluationDomainService {

    FragranticaEvaluation findByPerfumeIdOrderByVotesDesc(
        Long perfumeId
    );

}
