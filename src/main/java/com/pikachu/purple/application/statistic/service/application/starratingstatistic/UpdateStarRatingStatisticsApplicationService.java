package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.UpdateStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStarRatingStatisticsApplicationService implements
    UpdateStarRatingStatisticsUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public void invoke(Command command) {

        starRatingStatisticDomainService.increaseAllVotes(
            command.starRatingVOs()
        );
    }
}
