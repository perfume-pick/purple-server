package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewEvaluationId;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
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
@Table(name = "review_evaluation")
@IdClass(ReviewEvaluationId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEvaluationJpaEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewJpaEntity reviewJpaEntity;

    @Id
    @Column(name = "field_code", columnDefinition = "char(5)", nullable = false)
    private String fieldCode;

    @Id
    @Column(name = "option_code", columnDefinition = "char(5)", nullable = false)
    private String optionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

}
