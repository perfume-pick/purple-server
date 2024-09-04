package com.pikachu.purple.infrastructure.persistence.evaluation.entity;

import com.pikachu.purple.domain.evaluation.EvaluationCode;
import com.pikachu.purple.domain.evaluation.EvaluationFieldOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationCodeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "evaluation_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationCodeJpaEntity {

    @Id
    @Column(name = "code", columnDefinition = "char(5)")
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "varchar(255)", nullable = false)
    private EvaluationCodeType type;

    public static EvaluationCode toDomain(EvaluationCodeJpaEntity evaluationCodeJpaEntity) {
        return EvaluationCode.builder()
            .code(evaluationCodeJpaEntity.getCode())
            .name(evaluationCodeJpaEntity.getName())
            .type(evaluationCodeJpaEntity.getType())
            .build();
    }

}
