package com.pikachu.purple.application.perfume.scheduler;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoresUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PerfumeScheduler {

    private final RecalculatePerfumeAverageScoresUseCase recalculatePerfumeAverageScoresUseCase;

    @Transactional
    @Scheduled(cron = "${scheduler.daily-cron}")
    public void dailyRecalculatePerfumeAverageScores() {
        recalculatePerfumeAverageScoresUseCase.invoke();
    }
    
}
