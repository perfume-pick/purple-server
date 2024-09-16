package com.pikachu.purple.infrastructure.persistence.statistic.entity;

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

}
