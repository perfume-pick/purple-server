package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;

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

    List<EvaluationStatistic> findAllByStatisticsDate(String statisticsDate);

    void updateAll(
        String statisticsDate,
        List<EvaluationStatistic> evaluationStatistics
    );
}
