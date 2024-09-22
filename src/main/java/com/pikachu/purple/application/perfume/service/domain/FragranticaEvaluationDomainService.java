package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.FragranticaEvaluation;

public interface FragranticaEvaluationDomainService {

    FragranticaEvaluation findByPerfumeIdOrderByVotesDesc(Long perfumeId);

}
