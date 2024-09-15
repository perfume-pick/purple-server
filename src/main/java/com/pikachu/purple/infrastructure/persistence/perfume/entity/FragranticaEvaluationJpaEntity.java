package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.id.FragranticaEvaluationId;
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
@Table(name = "fragrantica_evaluation")
@IdClass(FragranticaEvaluationId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FragranticaEvaluationJpaEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @Column(name = "field_code", columnDefinition = "char(5)", nullable = false)
    private String fieldCode;

    @Id
    @Column(name = "option_code", columnDefinition = "char(5)", nullable = false)
    private String optionCode;

    @Column(name = "votes", nullable = false)
    private int votes;


}
