package com.pikachu.purple.infrastructure.persistence.evaluation.entity;

import com.pikachu.purple.domain.evaluation.enums.EvaluationCodeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "evaluation_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationCodeJpaEntity {

    @Id
    @Column(name = "code", columnDefinition = "char(5)", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "varchar(255)", nullable = false)
    private EvaluationCodeType type;

}
