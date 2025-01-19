package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DecreaseStarRatingStatisticApplicationService implements
    DecreaseStarRatingStatisticUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public void invoke(
        Long perfumeId,
        int score
    ) {
        starRatingStatisticDomainService.decreaseVotes(
            perfumeId,
            score
        );

    }

}
