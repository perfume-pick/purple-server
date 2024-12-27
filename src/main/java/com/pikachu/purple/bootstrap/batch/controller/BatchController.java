package com.pikachu.purple.bootstrap.batch.controller;

import com.pikachu.purple.application.statistic.scheduler.EvaluationStatisticScheduler;
//import com.pikachu.purple.application.statistic.scheduler.StarRatingStatisticScheduler;
import com.pikachu.purple.bootstrap.batch.api.BatchApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatchController implements BatchApi {

//    private final StarRatingStatisticScheduler starRatingStatisticScheduler;
    private final EvaluationStatisticScheduler evaluationStatisticScheduler;

    // TODO: Batch로 리팩토링 필요
//    @Override
//    public void countStarRatingStatistics() {
//        starRatingStatisticScheduler.dailyRecountStarRatingStatistics();
//    }

    // TODO: Batch로 리팩토링 필요
    @Override
    public void countEvaluationStatistics() {
        evaluationStatisticScheduler.dailyRecountEvaluationStatistics();
    }
}
