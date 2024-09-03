package com.pikachu.purple.infrastructure.persistence.evaluation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
    name = "evaluation_field_option",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_evaluation_field_option",
            columnNames = {"field_code", "option_code"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationFieldOptionJpaEntity {

    @Id
    @Column(name = "evaluation_field_option_id")
    private Long evaluationFieldOptionId;

    @Column(name = "field_code", columnDefinition = "char(5)", nullable = false)
    private String fieldCode;

    @Column(name = "option_code", columnDefinition = "char(5)", nullable = false)
    private String optionCode;

}
