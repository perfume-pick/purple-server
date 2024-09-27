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
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticJpaAdaptor implements EvaluationStatisticRepository {

    private final EvaluationStatisticJpaRepository evaluationStatisticJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;

    private EvaluationStatisticJpaEntity findEntityByToday(
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        String today = DateUtil.today();

        EvaluationStatisticCompositeKey compositeKey =
            EvaluationStatisticCompositeKey.builder()
                .statisticsDate(today)
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
                .statisticsDate(today)
                .perfumeJpaEntity(perfumeJpaEntity)
                .fieldCode(fieldCode)
                .optionCode(optionCode)
                .build();
        } else {
            return findResult.get();
        }
    }

    @Override
    public EvaluationStatistic findByPerfumeIdOrderByVotesDesc(Long perfumeId) {
        String today = DateUtil.today();

        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByTodayAndPerfumeIdOrderByVotesDesc(today, perfumeId);

        return EvaluationStatisticJpaEntity.toDomain(evaluationStatisticJpaEntities);
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

}
