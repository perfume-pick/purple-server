package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationStatisticDomainServiceImpl implements EvaluationStatisticDomainService {

    private final EvaluationStatisticRepository evaluationStatisticRepository;

    @Override
    public EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId) {
        return evaluationStatisticRepository.findByPerfumeIdOrderByVotesDesc(perfumeId);
    }

}
