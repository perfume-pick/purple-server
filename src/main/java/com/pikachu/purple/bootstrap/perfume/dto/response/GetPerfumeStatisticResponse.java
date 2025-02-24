package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticUseCase.Result;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticUseCase.StarRatingStatisticDTO;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import java.util.List;
import lombok.Getter;

@Getter
public class GetPerfumeStatisticResponse {

    private final List<StarRatingStatisticDTO> starRatingStatistics;
    private final List<EvaluationField<EvaluationOptionStatistic>> evaluationStatistics;

    private GetPerfumeStatisticResponse(
        List<StarRatingStatisticDTO> starRatingStatistics,
        List<EvaluationField<EvaluationOptionStatistic>> evaluationStatistics
    ) {
        this.starRatingStatistics = starRatingStatistics;
        this.evaluationStatistics = evaluationStatistics;
    }

    public static GetPerfumeStatisticResponse of(Result result) {
        return new GetPerfumeStatisticResponse(
            result.starRatingStatistics(),
            result.evaluationStatistics()
        );
    }
}
