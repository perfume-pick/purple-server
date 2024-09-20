package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.bootstrap.onboarding.vo.StarRatingInfo;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface StarRatingStatisticDomainService {

    List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId);

    void increaseVotes(
        Long perfumeId,
        int score
    );

    void increaseAllVotes(List<StarRatingInfo> starRatingInfos);

    void decreaseVotes(
        Long perfumeId,
        int score
    );
}
