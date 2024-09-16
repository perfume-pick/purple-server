package com.pikachu.purple.application.evaluation.port.out;

import com.pikachu.purple.domain.perfume.FragranticaEvaluation;

public interface FragranticaEvaluationRepository {

    FragranticaEvaluation findByPerfumeIdOrderByVotesDesc(Long perfumeId);

}
