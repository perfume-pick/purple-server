package com.pikachu.purple.application.statistic.port.in;

import com.pikachu.purple.application.statistic.common.dto.StarRatingStatisticDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import java.util.List;

public interface GetPerfumeStatisticByPerfumeIdUseCase {

    Result invoke(Long perfumeId);

    record Result(
        List<StarRatingStatisticDTO> starRatingStatistics,
        List<EvaluationFieldDTO<EvaluationOptionStatisticDTO>> evaluationStatistics
    ) {}

}
