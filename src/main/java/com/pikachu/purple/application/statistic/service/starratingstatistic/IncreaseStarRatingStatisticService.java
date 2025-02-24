package com.pikachu.purple.application.statistic.service.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.IncreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IncreaseStarRatingStatisticService implements
    IncreaseStarRatingStatisticUseCase {

    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    @DistributedLock(
        name = "increaseStarRatingStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(T(String).valueOf(#score))"
    )
    public void invoke(
        Long perfumeId,
        int score
    ) {
        starRatingStatisticRepository.increaseVotes(
            perfumeId,
            score
        );

    }

}
