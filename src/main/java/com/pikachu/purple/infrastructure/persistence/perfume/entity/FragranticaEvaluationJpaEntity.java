package com.pikachu.purple.infrastructure.persistence.perfume.entity;

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
        FragranticaEvaluation domain = new FragranticaEvaluation();
        for (FragranticaEvaluationJpaEntity jpaEntity : jpaEntities) {
            domain.set(
                EvaluationFieldType.of(jpaEntity.getFieldCode()),
                EvaluationOptionType.of(jpaEntity.getOptionCode()),
                jpaEntity.getVotes()
            );
        }

        return domain;
    }
}
