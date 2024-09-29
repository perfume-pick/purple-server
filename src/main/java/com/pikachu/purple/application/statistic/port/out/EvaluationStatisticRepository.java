package com.pikachu.purple.application.statistic.port.out;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;

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

    List<EvaluationStatistic> findAll(String statisticsDate);

    void updateAll(
        String statisticsDate,
        List<EvaluationStatistic> evaluationStatistics
    );

}
