package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface GetStarRatingStatisticsUseCase {

    Result invoke();

    record Result(List<StarRatingStatistic> starRatingStatistics) {};

}
