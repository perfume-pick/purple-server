package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface GetStarRatingStatisticsUseCase {

    Result invoke();

    Result invoke(Command command);

    record Command(Long perfumeId) {};

    record Result(List<StarRatingStatistic> starRatingStatistics) {};

}
