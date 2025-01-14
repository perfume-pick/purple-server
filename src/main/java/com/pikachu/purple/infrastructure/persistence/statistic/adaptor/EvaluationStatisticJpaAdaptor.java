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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationStatisticJpaAdaptor implements EvaluationStatisticRepository {

    private final EvaluationStatisticJpaRepository evaluationStatisticJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;

    private EvaluationStatisticJpaEntity findEntity(
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        EvaluationStatisticCompositeKey compositeKey =
            EvaluationStatisticCompositeKey.builder()
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
                .perfumeJpaEntity(perfumeJpaEntity)
                .fieldCode(fieldCode)
                .optionCode(optionCode)
                .build();
        } else {
            return findResult.get();
        }
    }

    @Override
    public EvaluationStatistic findOrderByVotesDesc(Long perfumeId) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAllByPerfumeIdOrderByVotesDesc(
                perfumeId);

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

        EvaluationStatisticJpaEntity evaluationStatisticJpaEntity = findEntity(
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
        EvaluationStatisticJpaEntity evaluationStatisticJpaEntity = findEntity(
            perfumeId,
            field.getCode(),
            option.getCode()
        );

        evaluationStatisticJpaEntity.decrease();
        evaluationStatisticJpaRepository.save(evaluationStatisticJpaEntity);
    }

    @Override
    public EvaluationStatistic findAll() {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities =
            evaluationStatisticJpaRepository.findAll(
                Sort.by(
                    Sort.Order.asc("perfumeJpaEntity.id"),
                    Sort.Order.asc("fieldCode"),
                    Sort.Order.asc("optionCode")
                )
            );

        return EvaluationStatisticJpaEntity.toDomain(evaluationStatisticJpaEntities);
    }

    @Override
    public void updateAll(EvaluationStatistic evaluationStatistic) {
        List<EvaluationStatisticJpaEntity> evaluationStatisticJpaEntities = new ArrayList<>();
        evaluationStatistic.getPerfumeIdSet().forEach(
            perfumeId -> {
                PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
                    .orElseThrow(() -> PerfumeNotFoundException);

                evaluationStatisticJpaEntities.addAll(
                    EvaluationStatisticJpaEntity.toJpaEntityList(
                        perfumeJpaEntity,
                        evaluationStatistic
                    )
                );
            }
        );

        evaluationStatisticJpaRepository.saveAll(evaluationStatisticJpaEntities);
    }

}
