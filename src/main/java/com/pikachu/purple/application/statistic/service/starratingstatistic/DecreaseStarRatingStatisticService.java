package com.pikachu.purple.application.statistic.service.starratingstatistic;

import com.pikachu.purple.application.statistic.port.in.starratingstatistic.DecreaseStarRatingStatisticUseCase;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DecreaseStarRatingStatisticService implements
    DecreaseStarRatingStatisticUseCase {

    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    @DistributedLock(
        name = "decreaseStarRatingStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(T(String).valueOf(#score))"
    )
    public void invoke(
        Long perfumeId,
        int score
    ) {
        StarRatingStatistic starRatingStatistic = findByPerfumeIdAndScore(
            perfumeId,
            score
        );

        if (starRatingStatistic.isZero()) {
            return;
        }
        starRatingStatisticRepository.decreaseVotes(
            perfumeId,
            score
        );
    }

    private StarRatingStatistic findByPerfumeIdAndScore(
        Long perfumeId,
        int score
    ) {
        return starRatingStatisticRepository.findByPerfumeIdAndScore(
            perfumeId,
            score
        );
    }

}
