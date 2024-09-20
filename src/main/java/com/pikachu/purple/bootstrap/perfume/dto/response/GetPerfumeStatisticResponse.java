package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.statistic.common.dto.StarRatingStatisticDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import java.util.List;

public record GetPerfumeStatisticResponse(
    List<StarRatingStatisticDTO> starRatingStatistics,
    List<EvaluationFieldDTO<EvaluationOptionStatisticDTO>> evaluationStatistics
) {}
