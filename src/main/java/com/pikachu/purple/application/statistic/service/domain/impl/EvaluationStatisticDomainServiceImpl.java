package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EvaluationStatisticDomainServiceImpl implements EvaluationStatisticDomainService {

    private final EvaluationStatisticRepository evaluationStatisticRepository;

    @Override
    public EvaluationStatistic findOrderByVotesDesc(Long perfumeId) {
        return evaluationStatisticRepository.findOrderByVotesDesc(perfumeId);
    }

    @Override
    @DistributedLock(
        name = "EvaluationStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(#field.code).concat('-').concat(#option.code)"
    )
    public void increaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        evaluationStatisticRepository.increaseVotes(
            perfumeId,
            field,
            option
        );
    }

    @Override
    @DistributedLock(
        name = "EvaluationStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(#field.code).concat('-').concat(#option.code)"
    )
    public void decreaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        evaluationStatisticRepository.decreaseVotes(
            perfumeId,
            field,
            option
        );
    }

    @Override
    public EvaluationStatistic findAll() {
        return evaluationStatisticRepository.findAll();
    }

    @Override
    public void updateAll(EvaluationStatistic evaluationStatistic) {
        evaluationStatisticRepository.updateAll(evaluationStatistic);
    }

}
