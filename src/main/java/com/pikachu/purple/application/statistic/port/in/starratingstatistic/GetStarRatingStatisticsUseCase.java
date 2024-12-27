package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface GetStarRatingStatisticsUseCase {

    Result invoke();

    Result invoke(Long perfumeId);

    Result invoke(List<Long> perfumeIds);

    record Result(List<StarRatingStatistic> starRatingStatistics) {};

}
