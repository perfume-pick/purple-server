package com.pikachu.purple.application.statistic.port.out;

import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface StarRatingStatisticRepository {

    List<StarRatingStatistic> findAll();

    List<StarRatingStatistic> findAllByPerfumeId(Long perfumeId);

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

    StarRatingStatistic find(
        Long perfumeId,
        int score
    );
}
