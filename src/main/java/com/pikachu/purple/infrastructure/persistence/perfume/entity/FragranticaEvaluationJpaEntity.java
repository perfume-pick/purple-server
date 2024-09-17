package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.perfume.FragranticaEvaluation;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.id.FragranticaEvaluationId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "fragrantica_evaluation")
@IdClass(FragranticaEvaluationId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FragranticaEvaluationJpaEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @Column(name = "field_code", columnDefinition = "char(5)")
    private String fieldCode;

    @Id
    @Column(name = "option_code", columnDefinition = "char(5)")
    private String optionCode;

    @Column(name = "votes")
    private int votes;

    public static FragranticaEvaluation toDomain(List<FragranticaEvaluationJpaEntity> jpaEntities) {
        Map<EvaluationFieldType, List<FragranticaEvaluationJpaEntity>> groupedByFieldType =
            jpaEntities.stream()
                .collect(Collectors.groupingBy(
                    jpaEntity -> EvaluationFieldType.of(jpaEntity.getFieldCode())
                ));

        List<EvaluationField<EvaluationOptionStatistic>> fields =
            groupedByFieldType.entrySet().stream()
                .map(entry -> {
                    EvaluationFieldType fieldType = entry.getKey();
                    List<EvaluationOptionStatistic> options = entry.getValue().stream()
                        .map(jpaEntity -> EvaluationOptionStatistic.builder()
                            .type(EvaluationOptionType.of(jpaEntity.getOptionCode()))
                            .votes(jpaEntity.getVotes())
                            .build())
                        .collect(Collectors.toList());

                    return EvaluationField.<EvaluationOptionStatistic>builder()
                        .type(fieldType)
                        .options(options)
                        .build();
            })
            .toList();

        return FragranticaEvaluation.builder()
            .fields(fields)
            .build();
    }
}
