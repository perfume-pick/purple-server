package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoreUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase.Command;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecalculatePerfumeAverageScoreApplicationService implements
    RecalculatePerfumeAverageScoreUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetStarRatingStatisticsUseCase getStarRatingStatisticsUseCase;

    @Override
    public void invoke(Command command) {
        List<StarRatingStatistic> starRatingStatistics = getStarRatingStatisticsUseCase
            .invoke(new GetStarRatingStatisticsUseCase.Command(command.perfumeId()))
            .starRatingStatistics();

        double totalScore = starRatingStatistics.stream()
            .mapToDouble(statistic -> statistic.getScore() * statistic.getVotes())
            .sum();

        int totalVotes = starRatingStatistics.stream()
            .mapToInt(StarRatingStatistic::getVotes)
            .sum();

        double averageScore = totalVotes > 0 ? totalScore / totalVotes : 0.0;
        perfumeDomainService.updateAverageScore(
            command.perfumeId(),
            averageScore
        );
    }

}
