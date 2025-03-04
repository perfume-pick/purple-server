package com.pikachu.purple.application.statistic.service.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticsUseCase;
import com.pikachu.purple.domain.review.StarRating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class IncreaseStarRatingStatisticsService implements
    IncreaseStarRatingStatisticsUseCase {

    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Transactional
    @Override
    public void invoke(List<StarRating> starRatings) {
        for (StarRating starRating : starRatings) {
            increaseStarRatingStatisticUseCase.invoke(
                starRating.getPerfume().getId(),
                starRating.getScore()
            );
        }
    }

}
