package com.pikachu.purple.bootstrap.admin.controller;

import com.pikachu.purple.application.perfume.port.in.perfume.RecalculatePerfumeAverageScoresUseCase;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.RecountEvaluationStatisticsUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.RecountStarRatingStatisticsUseCase;
import com.pikachu.purple.bootstrap.admin.api.AdminApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final RecountStarRatingStatisticsUseCase recountStarRatingStatisticsUseCase;
    private final RecalculatePerfumeAverageScoresUseCase recalculatePerfumeAverageScoresUseCase;
    private final RecountEvaluationStatisticsUseCase recountEvaluationStatisticsUseCase;

    @Override
    public void updateStarRatingStatistics() {
        recountStarRatingStatisticsUseCase.invoke();
    }

    @Override
    public void updatePerfumeAverageScores() {
        recalculatePerfumeAverageScoresUseCase.invoke();
    }

    @Override
    public void updateEvaluationStatistics() {
        recountEvaluationStatisticsUseCase.invoke();
    }
}
