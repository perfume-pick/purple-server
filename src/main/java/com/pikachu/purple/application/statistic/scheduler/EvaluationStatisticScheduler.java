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
        EvaluationStatistic theDayBeforeYesterdayEvaluationStatistic = evaluationStatisticDomainService
            .find(theDayBeforeYesterday);

        String yesterday = DateUtil.yesterday();
        List<Review> reviews =
            getReviewsDetailWithEvaluationByUpdatedDateUseCase.invoke(
                new GetReviewsDetailWithEvaluationByUpdatedDateUseCase.Command(yesterday)
            ).reviews();
        EvaluationStatistic yesterdayEvaluationStatistic = sum(reviews);

        EvaluationStatistic evaluationStatistic = calculateVotes(
            perfumeIds,
            theDayBeforeYesterdayEvaluationStatistic,
            yesterdayEvaluationStatistic
        );

        evaluationStatisticDomainService.update(
            yesterday,
            evaluationStatistic
        );

    }

    private EvaluationStatistic sum(List<Review> reviews) {
        EvaluationStatistic evaluationStatistic = new EvaluationStatistic();

        reviews.forEach(
            review -> review.getEvaluation().getFields(review.getId()).forEach(
                fieldType -> review.getEvaluation().getOptions(review.getId(), fieldType).forEach(
                    optionType -> evaluationStatistic.increase(
                        review.getPerfume().getId(),
                        fieldType,
                        optionType
                    )
                )
            )
        );

        return evaluationStatistic;
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
        EvaluationStatistic theDayBeforeYesterdayEvaluationStatistic,
        EvaluationStatistic yesterdayEvaluationStatistic
    ) {
        EvaluationStatistic evaluationStatistic = new EvaluationStatistic();
        perfumeIds.forEach(
            perfumeId -> Arrays.stream(EvaluationFieldType.values()).forEach(
                field -> field.getEvaluationOptionTypes().forEach(
                    option -> {
                        int theDayBeforeYesterdayVotes = theDayBeforeYesterdayEvaluationStatistic.getVotes(
                            perfumeId,
                            field,
                            option
                        );
                        int yesterdayVotes = yesterdayEvaluationStatistic.getVotes(
                            perfumeId,
                            field,
                            option
                        );

                        evaluationStatistic.set(
                            perfumeId,
                            field,
                            option,
                            theDayBeforeYesterdayVotes + yesterdayVotes
                        );
                    }
                )
            )
        );

        return evaluationStatistic;
    }

}
