package com.pikachu.purple.application.statistic.service.domain;

import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface StarRatingStatisticDomainService {

//    List<StarRatingStatistic> findAll(String statisticsDate);

    List<StarRatingStatistic> findAll(
//        String statisticsDate,
        Long perfumeId
    );

    void increaseVotes(
        Long perfumeId,
        int score
    );

    void decreaseVotes(
        Long perfumeId,
        int score
    );

//    void updateAll(
//        String statisticsDate,
//        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs
//    );

}
