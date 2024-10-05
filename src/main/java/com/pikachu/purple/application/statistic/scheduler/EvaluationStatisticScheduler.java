package com.pikachu.purple.application.statistic.scheduler;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsDetailWithEvaluationByUpdatedDateUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.util.DateUtil;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        EvaluationStatistic evaluationStatisticFound = evaluationStatisticDomainService
            .find(theDayBeforeYesterday);

        String yesterday = DateUtil.yesterday();
        List<Review> reviews =
            getReviewsDetailWithEvaluationByUpdatedDateUseCase.invoke(
                new GetReviewsDetailWithEvaluationByUpdatedDateUseCase.Command(yesterday)
            ).reviews();
        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
            reviewEvaluationCountMap = sumToMap(reviews);


        EvaluationStatistic evaluationStatistic = calculateVotes(
            perfumeIds,
            evaluationStatisticFound,
            reviewEvaluationCountMap
        );

        evaluationStatisticDomainService.update(
            yesterday,
            evaluationStatistic
        );

    }

    private Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
    sumToMap(List<Review> reviews) {
        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>>
            reviewEvaluationCountMap = new HashMap<>();

        reviews.forEach(
            review -> review.getEvaluation().getFields(review.getId()).forEach(
                fieldType -> review.getEvaluation().getOptions(review.getId(), fieldType).forEach(
                    optionType -> add(
                        reviewEvaluationCountMap,
                        review.getPerfume().getId(),
                        fieldType,
                        optionType
                    )
                )
            )
        );

        return reviewEvaluationCountMap;
    }

    private void add(
        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>> reviewEvaluationCountMap,
        Long perfumeId,
        EvaluationFieldType fieldType,
        EvaluationOptionType optionType
    ) {
        int votes = reviewEvaluationCountMap
            .getOrDefault(
                perfumeId,
                new EnumMap<>(EvaluationFieldType.class)
            ).getOrDefault(
                fieldType,
                new EnumMap<>(EvaluationOptionType.class)
            ).getOrDefault(
                optionType,
                0
            );

        reviewEvaluationCountMap
            .get(perfumeId)
            .get(fieldType)
            .put(optionType, votes + 1);

    }

    private EvaluationStatistic calculateVotes(
        List<Long> perfumeIds,
        EvaluationStatistic evaluationStatisticFound,
        Map<Long, Map<EvaluationFieldType, Map<EvaluationOptionType, Integer>>> reviewEvaluationCountMap
    ) {
        EvaluationStatistic evaluationStatistic = new EvaluationStatistic();
        perfumeIds.forEach(
            perfumeId -> Arrays.stream(EvaluationFieldType.values()).forEach(
                field -> field.getEvaluationOptionTypes().forEach(
                    option -> {
                        int previousVotes = evaluationStatisticFound.getVotes(
                            perfumeId,
                            field,
                            option
                        );
                        int yesterdayCounts = getMapValue(
                            reviewEvaluationCountMap,
                            perfumeId,
                            field,
                            option
                        );

                        evaluationStatistic.add(
                            perfumeId,
                            field,
                            option,
                            previousVotes + yesterdayCounts
                        );
                    }
                )
            )
        );

        return evaluationStatistic;
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
