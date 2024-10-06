package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.EvaluationStatisticJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.repository.EvaluationStatisticJpaRepository;
import com.pikachu.purple.infrastructure.persistence.statistic.vo.EvaluationStatisticCompositeKey;
import com.pikachu.purple.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticJpaAdaptor implements EvaluationStatisticRepository {

    private final EvaluationStatisticJpaRepository evaluationStatisticJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;

    private EvaluationStatisticJpaEntity findEntity(
        String statisticDates,
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        EvaluationStatisticCompositeKey compositeKey =
            EvaluationStatisticCompositeKey.builder()
                .statisticsDate(statisticDates)
                .perfumeId(perfumeId)
                .fieldCode(fieldCode)
                .optionCode(optionCode)
                .build();

        Optional<EvaluationStatisticJpaEntity> findResult =
            evaluationStatisticJpaRepository.findByCompositeKey(compositeKey);

        if (findResult.isEmpty()) {
            PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                .orElseThrow(() -> PerfumeNotFoundException);

            return EvaluationStatisticJpaEntity.builder()
                .statisticsDate(statisticDates)
                .perfumeJpaEntity(perfumeJpaEntity)
                .fieldCode(fieldCode)
                .optionCode(optionCode)
                .build();
        } else {
            return findResult.get();
        }
    }

    private EvaluationStatisticJpaEntity findEntityByToday(
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        String today = DateUtil.today();

        return findEntity(today, perfumeId, fieldCode, optionCode);
    }

    @Override
    public EvaluationStatistic findOrderByVotesDesc(
        String statisticsDate,
        Long perfumeId
    ) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByStatisticsDateAndPerfumeIdOrderByVotesDesc(
                statisticsDate,
                perfumeId
            );

        return EvaluationStatisticJpaEntity.toDomain(
            evaluationStatisticJpaEntities
        );
    }

    @Override
    public void increaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {

        EvaluationStatisticJpaEntity evaluationStatisticJpaEntity = findEntityByToday(
            perfumeId,
            field.getCode(),
            option.getCode()
        );

        evaluationStatisticJpaEntity.increase();
        evaluationStatisticJpaRepository.save(evaluationStatisticJpaEntity);
    }

    @Override
    public void decreaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        EvaluationStatisticJpaEntity evaluationStatisticJpaEntity = findEntityByToday(
            perfumeId,
            field.getCode(),
            option.getCode()
        );

        evaluationStatisticJpaEntity.decrease();
        evaluationStatisticJpaRepository.save(evaluationStatisticJpaEntity);
    }

    @Override
    public EvaluationStatistic find(String statisticsDate) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByStatisticsDate(statisticsDate);
        return EvaluationStatisticJpaEntity.toDomain(evaluationStatisticJpaEntities);
    }

    @Override
    public void update(
        String statisticsDate,
        EvaluationStatistic evaluationStatistic
    ) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities = new ArrayList<>();
        evaluationStatistic.getPerfumeIdSet().forEach(
            perfumeId -> {
                PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                    .orElseThrow(() -> PerfumeNotFoundException);

                evaluationStatisticJpaEntities.addAll(
                    EvaluationStatisticJpaEntity.toJpaEntityList(
                        statisticsDate,
                        perfumeJpaEntity,
                        evaluationStatistic
                    )
                );
            }
        );

        evaluationStatisticJpaRepository.saveAll(evaluationStatisticJpaEntities);
    }

}
