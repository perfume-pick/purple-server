package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoreUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecalculatePerfumeAverageScoreApplicationService implements
    RecalculatePerfumeAverageScoreUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final GetStarRatingStatisticsUseCase getStarRatingStatisticsUseCase;

    @Override
    @Transactional
    public void invoke() {
        List<StarRatingStatistic> starRatingStatistics = getStarRatingStatisticsUseCase.invoke()
            .starRatingStatistics();

        Map<Long, List<StarRatingStatistic>> perfumeStatistics = starRatingStatistics.stream()
            .collect(Collectors.groupingBy(starRatingStatistic -> starRatingStatistic.getPerfume().getId()));

        List<Perfume> perfumes = new ArrayList<>();
        for (Map.Entry<Long, List<StarRatingStatistic>> entry : perfumeStatistics.entrySet()) {
            Long perfumeId = entry.getKey();
            List<StarRatingStatistic> statistics = entry.getValue();

            double totalScore = statistics.stream()
                .mapToDouble(statistic -> statistic.getScore() * statistic.getVotes())
                .sum();
            int totalVotes = statistics.stream()
                .mapToInt(StarRatingStatistic::getVotes)
                .sum();

            double averageScore = totalVotes > 0 ? totalScore / totalVotes : 0.0;

            if (perfumeId.equals(Long.parseLong("2024120317162825464"))) {
                log.info(">>>>>> averageScore: {}", averageScore);
                log.info(">>>>>> perfume: {}", perfumeId);
            }
            perfumes.add(
                Perfume
                    .builder()
                    .id(perfumeId)
                    .averageScore(averageScore)
                    .build()
            );
        }

        perfumeDomainService.updateAllAverageScore(perfumes);
    }
}
