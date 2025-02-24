package com.pikachu.purple.application.statistic.service.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticsUseCase;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IncreaseStarRatingStatisticsService implements
    IncreaseStarRatingStatisticsUseCase {

    private final IncreaseStarRatingStatisticUseCase increaseStarRatingStatisticUseCase;

    @Override
    public void invoke(List<StarRatingVO> starRatingVOs) {
        for (StarRatingVO starRatingVO : starRatingVOs) {
            increaseStarRatingStatisticUseCase.invoke(
                starRatingVO.perfumeId(),
                starRatingVO.score()
            );
        }
    }

}
