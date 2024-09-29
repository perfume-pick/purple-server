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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticScheduler {
    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;
    private final EvaluationStatisticDomainService evaluationStatisticDomainService;
    private final GetReviewsDetailWithEvaluationByUpdatedDateUseCase getReviewsDetailWithEvaluationByUpdatedDateUseCase;

    @Transactional
    @Scheduled(cron = "${scheduler.daily-cron}")
    public void dailyRecountEvaluationStatistics() {
        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();

        String theDayBeforeYesterday = DateUtil.theDayBeforeYesterday();
        List<EvaluationStatistic> evaluationStatisticsFound = evaluationStatisticDomainService
            .findAll(theDayBeforeYesterday);

        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
            evaluationStatisticsMap = convertToMap(evaluationStatisticsFound);


        String yesterday = DateUtil.yesterday();
        List<Review> reviews =
            getReviewsDetailWithEvaluationByUpdatedDateUseCase.invoke(
                new GetReviewsDetailWithEvaluationByUpdatedDateUseCase.Command(yesterday)
            ).reviews();
        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
            reviewEvaluationCountMap = sumToMap(reviews);


        List<EvaluationStatistic> evaluationStatistics = perfumeIds.stream()
            .map(perfumeId -> {
                List<EvaluationField<EvaluationOptionStatistic>> evaluationFields = Arrays.stream(
                        EvaluationFieldType.values())
                    .map(fieldType -> {
                        List<EvaluationOptionStatistic> evaluationOptionStatistics =
                            fieldType.getEvaluationOptionTypes().stream()
                                .map(optionType -> {
                                    int previousVotes = getMapValue(
                                        evaluationStatisticsMap,
                                        perfumeId,
                                        fieldType,
                                        optionType
                                    );
                                    int yesterdayCounts = getMapValue(
                                        reviewEvaluationCountMap,
                                        perfumeId,
                                        fieldType,
                                        optionType
                                    );

                                    return EvaluationOptionStatistic.builder()
                                        .type(optionType)
                                        .votes(previousVotes + yesterdayCounts)
                                        .build();
                                })
                                .collect(Collectors.toList());

                        return EvaluationField.<EvaluationOptionStatistic>builder()
                            .type(fieldType)
                            .options(evaluationOptionStatistics)
                            .build();
                    })
                    .toList();

                return EvaluationStatistic.builder()
                    .perfume(Perfume.builder().id(perfumeId).build())
                    .fields(evaluationFields)
                    .build();
            })
            .toList();

        evaluationStatisticDomainService.updateAll(
            yesterday,
            evaluationStatistics
        );

    }

    private Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
    convertToMap(List<EvaluationStatistic> evaluationStatistics) {
        return evaluationStatistics.stream()
            .collect(Collectors.groupingBy(
                evaluationStatistic -> evaluationStatistic.getPerfume().getId(),
                Collectors.flatMapping(
                    evaluationStatistic -> evaluationStatistic.getFields().stream(),
                    Collectors.groupingBy(
                        EvaluationField::getType,
                        Collectors.flatMapping(
                            field -> field.getOptions().stream(),
                            Collectors.toMap(
                                EvaluationOptionStatistic::getType,
                                EvaluationOptionStatistic::getVotes
                            )
                        )
                    )
                )
            ));
    }

    private Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
    sumToMap(List<Review> reviews) {
        return reviews.stream()
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
