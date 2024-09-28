package com.pikachu.purple.application.statistic.scheduler;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUpdatedDateUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StarRatingStatisticScheduler {

    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;
    private final StarRatingStatisticDomainService starRatingStatisticDomainService;
    private final GetStarRatingsByUpdatedDateUseCase getStarRatingsByUpdateDateUseCase;

    @Scheduled(cron = "${scheduler.daily-cron}")
    protected void dailyRecountStarRatingStatistics() {
        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();

        String theDayBeforeYesterday = DateUtil.theDayBeforeYesterday();
        List<StarRatingStatistic> starRatingStatisticsFound = starRatingStatisticDomainService
            .findAll(theDayBeforeYesterday);
        Map<Long, Map<Integer, Integer>> starRatingStatisticMap = starRatingStatisticsFound.stream()
            .collect(Collectors.groupingBy(
                starRatingStatistic -> starRatingStatistic.getPerfume().getId(),
                Collectors.toMap(
                    StarRatingStatistic::getScore,
                    StarRatingStatistic::getVotes
                )
            ));

        String yesterday = DateUtil.yesterday();
        List<StarRating> starRatings = getStarRatingsByUpdateDateUseCase.invoke(
            new GetStarRatingsByUpdatedDateUseCase.Command(yesterday)
        ).starRatings();
        Map<Long, Map<Integer, Integer>> starRatingsCountsMap = starRatings.stream()
            .collect(Collectors.groupingBy(
                starRating -> starRating.getPerfume().getId(),
                Collectors.groupingBy(
                    StarRating::getScore,
                    Collectors.summingInt(starRating -> 1)
                )
            ));

        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs = new ArrayList<>();
        int[] scores = {1, 2, 3, 4, 5};
        for (Long perfumeId : perfumeIds) {
            List<StarRatingStatistic> starRatingStatistics = new ArrayList<>();
            for (int score : scores) {
                int previousVotes = getMapValue(starRatingStatisticMap, perfumeId, score);
                int yesterdayCounts = getMapValue(starRatingsCountsMap, perfumeId, score);

                StarRatingStatistic starRatingStatistic = StarRatingStatistic.builder()
                    .score(score)
                    .votes(previousVotes + yesterdayCounts)
                    .build();
                starRatingStatistics.add(starRatingStatistic);
            }
            perfumeStarRatingStatisticDTOs.add(
                PerfumeStarRatingStatisticDTO.builder()
                    .perfumeId(perfumeId)
                    .starRatingStatistics(starRatingStatistics)
                    .build()
            );
        }

        starRatingStatisticDomainService.updateAll(
            yesterday,
            perfumeStarRatingStatisticDTOs
        );

    }

    private int getMapValue(Map<Long, Map<Integer, Integer>> map, Long perfumeId, int score) {
        if (map.get(perfumeId) != null
            && map.get(perfumeId).get(score) != null) {
            return map.get(perfumeId).get(score);
        }
        return 0;
    }

}
