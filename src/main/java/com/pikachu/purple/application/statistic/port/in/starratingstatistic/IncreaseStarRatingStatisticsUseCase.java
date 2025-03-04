package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

import com.pikachu.purple.domain.review.StarRating;
import java.util.List;

public interface IncreaseStarRatingStatisticsUseCase {

    void invoke(List<StarRating> starRatings);

}
