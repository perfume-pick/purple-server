package com.pikachu.purple.infrastructure.persistence.statistic.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;

import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
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
    public EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId) {
        String today = DateUtil.today();

        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByTodayAndPerfumeIdOrderByVotesDesc(today, perfumeId);

        return EvaluationStatisticJpaEntity.toDomain(perfumeId, evaluationStatisticJpaEntities);
    }

    @Override
    public void increaseVotes(
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {

        EvaluationStatisticJpaEntity evaluationStatisticJpaEntity = findEntityByToday(
            perfumeId,
            fieldCode,
            optionCode
        );

        evaluationStatisticJpaEntity.increase();
        evaluationStatisticJpaRepository.save(evaluationStatisticJpaEntity);
    }

    @Override
    public void decreaseVotes(Long perfumeId, String fieldCode, String optionCode) {
        EvaluationStatisticJpaEntity evaluationStatisticJpaEntity = findEntityByToday(
            perfumeId,
            fieldCode,
            optionCode
        );

        evaluationStatisticJpaEntity.decrease();
        evaluationStatisticJpaRepository.save(evaluationStatisticJpaEntity);
    }

    @Override
    public List<EvaluationStatistic> findAll(String statisticsDate) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByStatisticsDate(statisticsDate);
        return EvaluationStatisticJpaEntity.toDomainList(evaluationStatisticJpaEntities);
    }

    @Override
    public void updateAll(
        String statisticsDate,
        List<EvaluationStatistic> evaluationStatistics
    ) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities = new ArrayList<>();
        for (EvaluationStatistic evaluationStatistic : evaluationStatistics) {
            Long perfumeId = evaluationStatistic.getPerfume().getId();
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

        evaluationStatisticJpaRepository.saveAll(evaluationStatisticJpaEntities);
    }

}
