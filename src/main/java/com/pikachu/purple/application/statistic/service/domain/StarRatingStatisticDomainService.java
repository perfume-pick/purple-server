package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface StarRatingStatisticDomainService {

    List<StarRatingStatistic> findAll();

    List<StarRatingStatistic> findAll(Long perfumeId);

    List<StarRatingStatistic> findAll(List<Long> perfumeIds);

    void updateAll(List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs);

    void increaseVotes(
        Long perfumeId,
        int score
    );

    void decreaseVotes(
        Long perfumeId,
        int score
    );

}
