package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoresUseCase;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.GetStarRatingStatisticsUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class RecalculatePerfumeAverageScoresService implements
    RecalculatePerfumeAverageScoresUseCase {

    private final PerfumeRepository perfumeRepository;
    private final GetStarRatingStatisticsUseCase getStarRatingStatisticsUseCase;

    @Override
    public void invoke() {
        List<StarRatingStatistic> starRatingStatistics = getStarRatingStatisticsUseCase.findAll()
            .starRatingStatistics();

        this.recalculatePerfumeAverageScores(starRatingStatistics);
    }


    @Override
    public void invoke(List<Long> perfumeIds) {
        List<StarRatingStatistic> starRatingStatistics = getStarRatingStatisticsUseCase
            .findAll(perfumeIds)
            .starRatingStatistics();

        this.recalculatePerfumeAverageScores(starRatingStatistics);
    }

    private void recalculatePerfumeAverageScores(List<StarRatingStatistic> starRatingStatistics) {
        Map<Long, List<StarRatingStatistic>> perfumeStatistics = starRatingStatistics.stream()
            .collect(Collectors.groupingBy(
                starRatingStatistic -> starRatingStatistic.getPerfume().getId()));

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

            perfumes.add(
                Perfume
                    .builder()
                    .id(perfumeId)
                    .averageScore(averageScore)
                    .build()
            );
        }

        perfumeRepository.updateAllAverageScore(perfumes);
    }
}
