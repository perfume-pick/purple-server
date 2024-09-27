package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.Builder;

@Builder
public record PerfumeStarRatingStatisticDTO(
    Long perfumeId,
    List<StarRatingStatistic> starRatingStatistics
) {}