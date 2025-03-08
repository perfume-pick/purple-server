package com.pikachu.purple.application.statistic.service.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetStarRatingStatisticsService implements GetStarRatingStatisticsUseCase {

    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    public Result findAll() {
        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticRepository.findAll();

        return new Result(starRatingStatistics);
    }

    @Override
    public Result findAllByPerfumeId(Long perfumeId) {
        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticRepository
            .findAllByPerfumeId(perfumeId);

        return new Result(starRatingStatistics);
    }

    @Override
    public Result findAll(List<Long> perfumeIds) {
        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticRepository
            .findAll(perfumeIds);

        return new Result(starRatingStatistics);
    }

}
