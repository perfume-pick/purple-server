package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.statistic.entity.id.EvaluationStatisticId;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "evaluation_statistic")
@IdClass(EvaluationStatisticId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationStatisticJpaEntity extends BaseEntity {

    @Id
    @Column(
        name = "statistics_date",
        columnDefinition = "char(8)",
        nullable = false
    )
    private String statisticsDate;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @Column(name = "field_code")
    private String fieldCode;

    @Id
    @Column(name = "option_code")
    private String optionCode;

    @Column(name = "votes")
    private int votes;

    public void increase() {
        this.votes++;
    }

    public void decrease() {
        this.votes--;
    }

    public static EvaluationStatistic toDomain(List<EvaluationStatisticJpaEntity> jpaEntities) {
        Map<EvaluationFieldType, List<EvaluationStatisticJpaEntity>> groupedByFieldType =
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

        return EvaluationStatistic.builder()
            .fields(fields)
            .build();
    }
}
