package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;

public interface EvaluationStatisticDomainService {

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
