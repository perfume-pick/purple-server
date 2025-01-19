package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IncreaseStarRatingStatisticsApplicationService implements
    IncreaseStarRatingStatisticsUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public void invoke(List<StarRatingVO> starRatingVOs) {
        for (StarRatingVO starRatingVO : starRatingVOs) {
            starRatingStatisticDomainService.increaseVotes(
                starRatingVO.perfumeId(),
                starRatingVO.score()
            );
        }
    }

}
