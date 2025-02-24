package com.pikachu.purple.application.statistic.service.starratingstatistic;

import static com.pikachu.purple.util.StringUtil.DELIMITER;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.statistic.port.in.starratingstatistic.RecountStarRatingStatisticsUseCase;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RecountStarRatingStatisticsService implements
    RecountStarRatingStatisticsUseCase {

    private final StarRatingStatisticRepository starRatingStatisticRepository;
    private final GetStarRatingsUseCase getStarRatingsUseCase;
    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;

    @Override
    public void invoke() {
        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();
        // 별점 다 가져오기
        List<StarRating> starRatings = getStarRatingsUseCase.findAll().starRatings();
        // perfumeId, score 기준으로 그룹화
        Map<String, Integer> starRatingStatisticMap = starRatings.stream()
            .collect(Collectors.groupingBy(
                starRating -> buildMapKey(
                    starRating.getPerfume().getId(),
                    starRating.getScore()
                ),
                Collectors.summingInt(starRating -> 1)
            ));

        int[] scores = {1, 2, 3, 4, 5};
        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs = new ArrayList<>();
        for (Long perfumeId : perfumeIds) {
            List<StarRatingStatistic> starRatingStatistics = new ArrayList<>();
            for (int score : scores) {
                String key = buildMapKey(
                    perfumeId,
                    score
                );
                int votes = starRatingStatisticMap.getOrDefault(key, 0);

                StarRatingStatistic starRatingStatistic = StarRatingStatistic.builder()
                    .score(score)
                    .votes(votes)
                    .build();
                starRatingStatistics.add(starRatingStatistic);
            }

            perfumeStarRatingStatisticDTOs.add(
                PerfumeStarRatingStatisticDTO.builder()
                    .perfumeId(perfumeId)
                    .starRatingStatistics(starRatingStatistics)
                    .build()
            );
        }

        starRatingStatisticRepository.updateAll(
            perfumeStarRatingStatisticDTOs
        );

    }

    private String buildMapKey(Long perfumeId, int score) {
        return perfumeId + DELIMITER + score;
    }

}
