package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import java.util.List;
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
    public List<EvaluationStatistic> findAll(String statisticsDate) {
        return evaluationStatisticRepository.findAll(statisticsDate);
    }

    @Override
    public void updateAll(
        String statisticsDate,
        List<EvaluationStatistic> evaluationStatistics
    ) {
        evaluationStatisticRepository.updateAll(
            statisticsDate,
            evaluationStatistics
        );
    }

}
