package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;

public interface EvaluationStatisticDomainService {

    EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId);

}
