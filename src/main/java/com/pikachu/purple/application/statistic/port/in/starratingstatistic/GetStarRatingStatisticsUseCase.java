package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface GetStarRatingStatisticsUseCase {

    Result findAll();

    Result findAll(Long perfumeId);

    Result findAll(List<Long> perfumeIds);

    record Result(List<StarRatingStatistic> starRatingStatistics) {};

}
