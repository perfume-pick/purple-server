package com.pikachu.purple.application.statistic.port.in;

import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;

public interface GetPerfumeStatisticUseCase {

    Result find(Long perfumeId);

    record Result(
        List<StarRatingStatisticDTO> starRatingStatistics,
        List<EvaluationField<EvaluationOptionStatistic>> evaluationStatistics
    ) {}

    record StarRatingStatisticDTO(
        int score,
        int votePercent
    ) {

        public static StarRatingStatisticDTO of(
            StarRatingStatistic starRatingStatistic,
            int totalVotes
        ) {
            return new StarRatingStatisticDTO(
                starRatingStatistic.getScore(),
                MathUtil.getPercentage(
                    starRatingStatistic.getVotes(),
                    totalVotes
                )
            );
        }

    }

}
