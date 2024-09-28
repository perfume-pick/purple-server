package com.pikachu.purple.application.statistic.scheduler;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsDetailWithEvaluationByUpdatedDateUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.util.DateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticScheduler {
    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;
    private final EvaluationStatisticDomainService evaluationStatisticDomainService;
    private final GetReviewsDetailWithEvaluationByUpdatedDateUseCase getReviewsDetailWithEvaluationByUpdatedDateUseCase;

    @Scheduled(cron = "${scheduler.daily-cron}")
    protected void dailyRecountEvaluationStatistics() {
        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();

        String theDayBeforeYesterday = DateUtil.theDayBeforeYesterday();
        List<EvaluationStatistic> evaluationStatisticsFound = evaluationStatisticDomainService
            .findAllByStatisticsDate(theDayBeforeYesterday);
        Map<Long, List<EvaluationStatistic>> statisticsGroupedByPerfumeId =
            evaluationStatisticsFound.stream()
                .collect(Collectors.groupingBy(
                    evaluationStatistic -> evaluationStatistic.getPerfume().getId()
                ));

        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>> evaluationStatisticsMap =
            new HashMap<>();
        for (Map.Entry<Long, List<EvaluationStatistic>> statisticSet :
            statisticsGroupedByPerfumeId.entrySet()) {
            for (EvaluationStatistic statistic : statisticSet.getValue()) {
                Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>> fieldMap =
                    new HashMap<>();
                for (EvaluationField<EvaluationOptionStatistic> field : statistic.getFields()) {
                    Map<EvaluationOptionType, Integer> optionMap = new HashMap<>();
                    for (EvaluationOptionStatistic optionStatistic : field.getOptions()) {
                        optionMap.put(optionStatistic.getType(), optionStatistic.getVotes());
                    }
                    fieldMap.put(field.getType(), optionMap);
                }
                evaluationStatisticsMap.put(statisticSet.getKey(), fieldMap);
            }
        }


        String yesterday = DateUtil.yesterday();
        List<Review> reviews =
            getReviewsDetailWithEvaluationByUpdatedDateUseCase.invoke(
                new GetReviewsDetailWithEvaluationByUpdatedDateUseCase.Command(yesterday)
            ).reviews();
        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>> reviewEvaluationCountMap =
            reviews.stream()
                .collect(Collectors.groupingBy(
                    review -> review.getPerfume().getId(),
                    Collectors.flatMapping(
                        review -> review.getEvaluation().getFields().stream(),
                        Collectors.groupingBy(
                            EvaluationField::getType,
                            Collectors.flatMapping(
                                field -> field.getOptions().stream(),
                                Collectors.groupingBy(
                                    EvaluationOption::getType,
                                    Collectors.summingInt(option -> 1)
                                )
                            )
                        )
                    )
                ));

        List<EvaluationStatistic> evaluationStatistics = new ArrayList<>();
        for (Long perfumeId : perfumeIds) {
            List<EvaluationField<EvaluationOptionStatistic>> evaluationFields = new ArrayList<>();
            for (EvaluationFieldType fieldType : EvaluationFieldType.values()) {
                List<EvaluationOptionStatistic> evaluationOptionStatistics = new ArrayList<>();
                for (EvaluationOptionType optionType : fieldType.getEvaluationOptionTypes()) {
                    int previousVotes = getMapValue(evaluationStatisticsMap, perfumeId, fieldType, optionType);
                    int yesterdayCounts = getMapValue(reviewEvaluationCountMap, perfumeId, fieldType, optionType);

                    evaluationOptionStatistics.add(
                        EvaluationOptionStatistic.builder()
                            .type(optionType)
                            .votes(previousVotes + yesterdayCounts)
                            .build()
                    );
                }
                evaluationFields.add(
                    EvaluationField.<EvaluationOptionStatistic>builder()
                        .type(fieldType)
                        .options(evaluationOptionStatistics)
                        .build()
                );
            }
            evaluationStatistics.add(
                EvaluationStatistic.builder()
                    .perfume(Perfume.builder().id(perfumeId).build())
                    .fields(evaluationFields)
                    .build()
            );
        }

        evaluationStatisticDomainService.updateAll(
            yesterday,
            evaluationStatistics
        );

    }

    private int getMapValue(Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>> map, Long perfumeId, EvaluationFieldType fieldType, EvaluationOptionType optionType) {
        if (map.get(perfumeId) != null
            && map.get(perfumeId).get(fieldType) != null
            && map.get(perfumeId).get(fieldType).get(optionType) != null)
        {
            return map.get(perfumeId).get(fieldType).get(optionType);
        }
        return 0;
    }
    
}
