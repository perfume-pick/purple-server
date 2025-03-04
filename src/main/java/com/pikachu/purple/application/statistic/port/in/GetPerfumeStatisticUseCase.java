package com.pikachu.purple.application.statistic.port.in;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import java.util.List;

public interface GetPerfumeStatisticUseCase {

    Result findByPerfumeId(Long perfumeId);

    record Result(
        List<StarRatingStatisticDTO> starRatingStatistics,
        List<EvaluationField<EvaluationOptionStatistic>> evaluationStatistics
    ) {}

    record StarRatingStatisticDTO(
        int score,
        int votePercent
    ) {}

}
