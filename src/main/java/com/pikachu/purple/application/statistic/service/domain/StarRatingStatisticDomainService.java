package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface StarRatingStatisticDomainService {

    List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId);

    void increaseVotes(
        Long perfumeId,
        int score
    );

    void decreaseVotes(
        Long perfumeId,
        int score
    );

    List<StarRatingStatistic> findAll(String statisticsDate);

    void updateAll(
        String statisticsDate,
        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs
    );

}
