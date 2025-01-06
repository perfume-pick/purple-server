package com.pikachu.purple.application.statistic.scheduler;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.RecountStarRatingStatisticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class StarRatingStatisticScheduler {

    private final RecountStarRatingStatisticsUseCase recountStarRatingStatisticsUseCase;

    @Transactional
    @Scheduled(cron = "${scheduler.daily-cron}")
    public void dailyRecountStarRatingStatistics() {
        recountStarRatingStatisticsUseCase.invoke();
    }

}
