package com.pikachu.purple.bootstrap.admin.controller;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoreUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.RecountStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.scheduler.EvaluationStatisticScheduler;
import com.pikachu.purple.bootstrap.admin.api.AdminApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final RecountStarRatingStatisticsUseCase recountStarRatingStatisticsUseCase;
    private final RecalculatePerfumeAverageScoreUseCase recalculatePerfumeAverageScoreUseCase;
    private final EvaluationStatisticScheduler evaluationStatisticScheduler;

    @Override
    public void updateStarRatingStatistics() {
        recountStarRatingStatisticsUseCase.invoke();
    }

    @Override
    public void updatePerfumeAverageScores() {
        recalculatePerfumeAverageScoreUseCase.invoke();
    }

    // TODO: Batch로 리팩토링 필요
    @Override
    public void countEvaluationStatistics() {
        evaluationStatisticScheduler.dailyRecountEvaluationStatistics();
    }
}
