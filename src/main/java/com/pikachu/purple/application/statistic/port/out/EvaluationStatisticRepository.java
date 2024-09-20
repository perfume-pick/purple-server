package com.pikachu.purple.application.statistic.port.out;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;

public interface EvaluationStatisticRepository {

    EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId);

}
