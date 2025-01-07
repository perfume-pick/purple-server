package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;

public interface EvaluationStatisticDomainService {

    EvaluationStatistic findOrderByVotesDesc(Long perfumeId);

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

    EvaluationStatistic findAll();

    void updateAll(EvaluationStatistic evaluationStatistic);

}
