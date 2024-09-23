package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;

public interface EvaluationStatisticDomainService {

    EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId);

    void increaseVotes(
        Long perfumeId,
        String fieldCode,
        String optionCode
    );

    void decreaseVotes(
        Long perfumeId,
        String fieldCode,
        String optionCode
    );
}
