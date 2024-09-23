package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
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

    @Override
    @DistributedLock(
        name = "increaseStarRatingStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(#fieldCode).concat('-').concat(#optionCode)"
    )
    public void increaseVotes(
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        evaluationStatisticRepository.increaseVotes(
            perfumeId,
            fieldCode,
            optionCode
        );
    }

    @Override
    public void decreaseVotes(
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        evaluationStatisticRepository.decreaseVotes(
            perfumeId,
            fieldCode,
            optionCode
        );
    }

}
