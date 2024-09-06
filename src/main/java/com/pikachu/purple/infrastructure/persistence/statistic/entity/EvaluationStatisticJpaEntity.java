package com.pikachu.purple.infrastructure.persistence.statistic.entity;

import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "evaluation_statistic")
@IdClass(EvaluationStatisticId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationStatisticJpaEntity extends BaseEntity {

    @Id
    @Column(name = "perfume_id")
    private Long perfumeId;

    @Id
    @Column(name = "field_code")
    private String fieldCode;

    @Id
    @Column(name = "option_code")
    private String optionCode;

    @Column(name = "votes")
    private int votes;

}
