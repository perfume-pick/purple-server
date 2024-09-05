package com.pikachu.purple.infrastructure.persistence.evaluation.entity;

import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;
import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOption;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "fragrantica_evaluation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FragranticaEvaluationJpaEntity {

    @Id
    @Column(name = "fragrantica_evaluation_id")
    private Long fragranticaEvaluationId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "field_code", columnDefinition = "char(5)", nullable = false)
    private String fieldCode;

    @Column(name = "option_code", columnDefinition = "char(5)", nullable = false)
    private String optionCode;

    @Column(name = "votes", nullable = false)
    private int votes;

    public static FragranticaEvaluation toDomain(FragranticaEvaluationJpaEntity jpaEntity) {

        return FragranticaEvaluation.builder()
            .fragranticaEvaluationId(jpaEntity.getFragranticaEvaluationId())
            .perfumeId(jpaEntity.getPerfumeId())
            .field(EvaluationField.of(jpaEntity.getFieldCode()))
            .option(EvaluationOption.of(jpaEntity.getOptionCode()))
            .votes(jpaEntity.getVotes())
            .build();
    }

}
