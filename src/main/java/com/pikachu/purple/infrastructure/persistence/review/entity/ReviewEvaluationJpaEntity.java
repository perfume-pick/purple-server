package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewEvaluationId;
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
@Table(name = "review_evaluation")
@IdClass(ReviewEvaluationId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEvaluationJpaEntity extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewJpaEntity reviewJpaEntity;

    @Id
    @Column(
        name = "field_code",
        columnDefinition = "char(5)"
    )
    private String fieldCode;

    @Id
    @Column(
        name = "option_code",
        columnDefinition = "char(5)"
    )
    private String optionCode;

    public static List<ReviewEvaluationJpaEntity> toJpaEntityList(
        ReviewJpaEntity reviewJpaEntity,
        ReviewEvaluation reviewEvaluation
    ) {
        List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities = new ArrayList<>();

        Long reviewId = reviewJpaEntity.getId();
        reviewEvaluation.getFields(reviewId).forEach(
            field -> {
                reviewEvaluation.getOptions(reviewId, field).forEach(
                    option -> reviewEvaluationJpaEntities.add(
                        ReviewEvaluationJpaEntity.builder()
                            .reviewJpaEntity(reviewJpaEntity)
                            .fieldCode(field.getCode())
                            .optionCode(option.getCode())
                            .build()
                    )
                );
            }
        );
        return reviewEvaluationJpaEntities;
    }

    public static ReviewEvaluation toDomain(List<ReviewEvaluationJpaEntity> jpaEntities) {
        ReviewEvaluation domain = new ReviewEvaluation();
        for (ReviewEvaluationJpaEntity jpaEntity : jpaEntities) {
            domain.add(
                jpaEntity.getReviewJpaEntity().getId(),
                EvaluationFieldType.of(jpaEntity.getFieldCode()),
                EvaluationOptionType.of(jpaEntity.getOptionCode())
            );
        }

        return domain;
    }

}
