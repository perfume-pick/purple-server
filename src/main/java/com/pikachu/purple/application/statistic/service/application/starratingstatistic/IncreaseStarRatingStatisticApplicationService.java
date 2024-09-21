package com.pikachu.purple.application.statistic.service.application.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncreaseStarRatingStatisticApplicationService implements
    IncreaseStarRatingStatisticUseCase {

    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public void invoke(Command command) {

        starRatingStatisticDomainService.increaseVotes(
            command.perfumeId(),
            command.score()
        );

    }

}
