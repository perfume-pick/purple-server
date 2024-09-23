package com.pikachu.purple.application.statistic.port.out;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;

public interface EvaluationStatisticRepository {

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
