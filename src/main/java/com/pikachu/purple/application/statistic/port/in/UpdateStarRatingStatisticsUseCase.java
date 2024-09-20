package com.pikachu.purple.application.statistic.port.in;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import java.util.List;

public interface UpdateStarRatingStatisticsUseCase {

    void invoke(Command command);

    record Command(
        List<StarRatingVO> starRatingVOs
    ) {}

}
