package com.pikachu.purple.application.statistic.port.out;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;

public interface EvaluationStatisticRepository {

    EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId);

    void increaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    );

    void decreaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    );

    List<EvaluationStatistic> findAll(String statisticsDate);

    void updateAll(
        String statisticsDate,
        List<EvaluationStatistic> evaluationStatistics
    );

}
