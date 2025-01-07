package com.pikachu.purple.application.statistic.scheduler;

import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.RecountEvaluationStatisticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticScheduler {
    private final RecountEvaluationStatisticsUseCase recountEvaluationStatisticsUseCase;

    @Transactional
    @Scheduled(cron = "${scheduler.daily-evaluation-statistic-cron}")
    public void dailyRecountEvaluationStatistics() {
        recountEvaluationStatisticsUseCase.invoke();
    }

}
