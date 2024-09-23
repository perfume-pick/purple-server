package com.pikachu.purple.application.statistic.port.in.starratingstatistic;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import java.util.List;

public interface IncreaseStarRatingStatisticsUseCase {

    void invoke(Command command);

    record Command(
        List<StarRatingVO> starRatingVOs
    ) {}

}
