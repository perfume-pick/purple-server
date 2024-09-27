package com.pikachu.purple.application.statistic.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EvaluationStatisticScheduler {

    @Scheduled(cron = "${scheduler.daily-cron}")
    protected void dailyRecountEvaluationStatistics() {

    }
    
}
