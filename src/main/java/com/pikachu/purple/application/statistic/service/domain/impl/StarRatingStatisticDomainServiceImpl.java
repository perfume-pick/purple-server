package com.pikachu.purple.application.statistic.service.domain.impl;

import com.pikachu.purple.application.review.service.domain.impl.StarRatingDomainServiceImpl;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingVO;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StarRatingStatisticDomainServiceImpl implements StarRatingStatisticDomainService {

    private final StarRatingStatisticRepository starRatingStatisticRepository;
    private final StarRatingDomainServiceImpl starRatingDomainServiceImpl;

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
    public void increaseAllVotes(List<StarRatingVO> starRatingVOs) {
        for (StarRatingVO starRatingVO : starRatingVOs) {
            increaseVotes(
                starRatingVO.perfumeId(),
                starRatingVO.score()
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
