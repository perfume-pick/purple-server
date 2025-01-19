package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoreUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class RecalculatePerfumeAverageScoreApplicationService implements
    RecalculatePerfumeAverageScoreUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetStarRatingStatisticsUseCase getStarRatingStatisticsUseCase;

    @Override
    @Transactional
    public void invoke(Long perfumeId) {
        List<StarRatingStatistic> starRatingStatistics = getStarRatingStatisticsUseCase
            .invoke(perfumeId)
            .starRatingStatistics();

        double totalScore = starRatingStatistics.stream()
            .mapToDouble(statistic -> statistic.getScore() * statistic.getVotes())
            .sum();

        int totalVotes = starRatingStatistics.stream()
            .mapToInt(StarRatingStatistic::getVotes)
            .sum();

        double averageScore = totalVotes > 0 ? totalScore / totalVotes : 0.0;
        perfumeDomainService.updateAverageScore(
            perfumeId,
            averageScore
        );
    }

}
