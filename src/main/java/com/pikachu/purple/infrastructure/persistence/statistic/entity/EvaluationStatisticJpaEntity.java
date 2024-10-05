package com.pikachu.purple.infrastructure.persistence.statistic.entity;

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
import java.util.ArrayList;
import java.util.List;
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

    public static EvaluationStatistic toDomain(
        List<EvaluationStatisticJpaEntity> jpaEntities
    ) {
        EvaluationStatistic domain = new EvaluationStatistic();
        for (EvaluationStatisticJpaEntity jpaEntity : jpaEntities) {
            domain.add(
                jpaEntity.getPerfumeJpaEntity().getId(),
                EvaluationFieldType.of(jpaEntity.getFieldCode()),
                EvaluationOptionType.of(jpaEntity.getOptionCode()),
                jpaEntity.getVotes()
            );
        }

        return domain;
    }

    public static List<EvaluationStatisticJpaEntity> toJpaEntityList(
        String statisticsDate,
        PerfumeJpaEntity perfumeJpaEntity,
        EvaluationStatistic evaluationStatistic
    ) {
        List<EvaluationStatisticJpaEntity> jpaEntities = new ArrayList<>();
        evaluationStatistic.getFields(perfumeJpaEntity.getId()).forEach(
            field -> evaluationStatistic.getOptions(
                perfumeJpaEntity.getId(),
                field
            ).forEach(
                option -> {
                    int votes = evaluationStatistic.getVotes(
                        perfumeJpaEntity.getId(),
                        field,
                        option
                    );

                    jpaEntities.add(
                        EvaluationStatisticJpaEntity.builder()
                            .statisticsDate(statisticsDate)
                            .perfumeJpaEntity(perfumeJpaEntity)
                            .fieldCode(field.getCode())
                            .optionCode(option.getCode())
                            .votes(votes)
                            .build()
                    );
                }
            )
        );

        return jpaEntities;
    }
}
