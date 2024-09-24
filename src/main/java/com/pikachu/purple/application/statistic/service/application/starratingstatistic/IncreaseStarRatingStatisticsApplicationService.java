package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncreaseStarRatingStatisticsApplicationService implements
    IncreaseStarRatingStatisticsUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public void invoke(Command command) {

        for (StarRatingVO starRatingVO : command.starRatingVOs()) {
            starRatingStatisticDomainService.increaseVotes(
                starRatingVO.perfumeId(),
                starRatingVO.score()
            );
        }

    }

}
