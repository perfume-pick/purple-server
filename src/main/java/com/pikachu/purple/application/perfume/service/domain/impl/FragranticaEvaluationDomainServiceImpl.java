package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.FragranticaEvaluationRepository;
import com.pikachu.purple.application.perfume.service.domain.FragranticaEvaluationDomainService;
import com.pikachu.purple.domain.perfume.FragranticaEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FragranticaEvaluationDomainServiceImpl implements FragranticaEvaluationDomainService {

    private final FragranticaEvaluationRepository fragranticaEvaluationRepository;

    @Override
    public FragranticaEvaluation findByPerfumeIdOrderByVotesDesc(Long perfumeId) {
        return fragranticaEvaluationRepository.findByPerfumeIdOrderByVotesDesc(perfumeId);
    }

}
