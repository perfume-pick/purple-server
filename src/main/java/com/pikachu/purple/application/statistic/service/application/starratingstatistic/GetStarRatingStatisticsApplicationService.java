package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetStarRatingStatisticsApplicationService implements GetStarRatingStatisticsUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public Result invoke() {
        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticDomainService.findAll();

        return new Result(starRatingStatistics);
    }

    @Override
    public Result invoke(Long perfumeId) {
        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticDomainService
            .findAll(perfumeId);

        return new Result(starRatingStatistics);
    }

    @Override
    public Result invoke(List<Long> perfumeIds) {
        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticDomainService
            .findAll(perfumeIds);

        return new Result(starRatingStatistics);
    }

}
