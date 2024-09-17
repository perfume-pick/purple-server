package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
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

    public static List<ReviewEvaluationJpaEntity> toJpaEntityList(ReviewEvaluation domain) {

        List<ReviewEvaluationJpaEntity> jpaEntities = new ArrayList<>();
        for (EvaluationField<EvaluationOption> field : domain.getFields()) {
            EvaluationFieldType fieldType = field.getType();
            for (EvaluationOption option : field.getOptions()) {
                EvaluationOptionType optionType = option.getType();

            }
        }
    }

}
