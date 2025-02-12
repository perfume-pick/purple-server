package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StarRatingStatisticDomainServiceImpl implements StarRatingStatisticDomainService {

    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    public List<StarRatingStatistic> findAll() {
        return starRatingStatisticRepository.findAll();
    }

    @Override
    public List<StarRatingStatistic> findAll(Long perfumeId) {
        return starRatingStatisticRepository.findAll(perfumeId);
    }

    @Override
    public List<StarRatingStatistic> findAll(List<Long> perfumeIds) {
        return starRatingStatisticRepository.findAll(perfumeIds);
    }

    @Override
    public void updateAll(List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs) {
        starRatingStatisticRepository.updateAll(perfumeStarRatingStatisticDTOs);
    }

    @Override
    @DistributedLock(
        name = "increaseStarRatingStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(T(String).valueOf(#score))"
    )
    public void increaseVotes(
        Long perfumeId,
        int score
    ) {
        starRatingStatisticRepository.increaseVotes(
            perfumeId,
            score
        );
    }

    @Override
    @DistributedLock(
        name = "decreaseStarRatingStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(T(String).valueOf(#score))"
    )
    public void decreaseVotes(
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
