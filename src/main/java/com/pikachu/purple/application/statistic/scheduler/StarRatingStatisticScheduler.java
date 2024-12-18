package com.pikachu.purple.application.statistic.scheduler;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUpdatedDateUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StarRatingStatisticScheduler {

    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;
    private final StarRatingStatisticDomainService starRatingStatisticDomainService;
    private final GetStarRatingsByUpdatedDateUseCase getStarRatingsByUpdateDateUseCase;

    @Transactional
    @Scheduled(cron = "${scheduler.daily-cron}")
    public void duplicatePreviousDayRows() {
        String yesterday = DateUtil.yesterday();
        List<StarRatingStatistic> yesterdayStarRatingStatistic = starRatingStatisticDomainService
            .findAll(yesterday);

        Map<Perfume, List<StarRatingStatistic>> yesterdayStarRatingStatisticMap = yesterdayStarRatingStatistic.stream()
            .collect(Collectors.groupingBy(StarRatingStatistic::getPerfume));

        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs = new ArrayList<>();
        for (Map.Entry<Perfume, List<StarRatingStatistic>> entry :
            yesterdayStarRatingStatisticMap.entrySet()) {
            Perfume perfume = entry.getKey();
            List<StarRatingStatistic> starRatingStatistics = entry.getValue();

            perfumeStarRatingStatisticDTOs.add(
                PerfumeStarRatingStatisticDTO
                    .builder()
                    .perfumeId(perfume.getId())
                    .starRatingStatistics(starRatingStatistics)
                    .build()
            );
        }

        String today = DateUtil.today();
        starRatingStatisticDomainService.createAll(
            today,
            perfumeStarRatingStatisticDTOs
        );
    }
}
