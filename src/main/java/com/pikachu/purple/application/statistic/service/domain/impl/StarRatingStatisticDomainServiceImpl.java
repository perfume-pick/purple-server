package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarRatingStatisticDomainServiceImpl implements StarRatingStatisticDomainService {

    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    public List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId) {
        return starRatingStatisticRepository.findAllByPerfumeId(perfumeId);
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
    public void increaseAllVotes(List<StarRatingInfo> starRatingInfos) {
        for (StarRatingInfo starRatingInfo : starRatingInfos) {
            increaseVotes(
                starRatingInfo.perfumeId(),
                starRatingInfo.score()
            );
        }
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
        starRatingStatisticRepository.decreaseVotes(
            perfumeId,
            score
        );
    }

}
