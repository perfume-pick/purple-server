package com.pikachu.purple.infrastructure.persistence.userevaluation.entity;

import com.pikachu.purple.domain.user.entity.UserEvaluation;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_evaluation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvaluationJpaEntity extends BaseEntity {

    @Id
    @Column(name = "user_evaluation_id")
    private Long userEvaluationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "field_code", nullable = false, columnDefinition = "char(5)")
    private String fieldCode;

    @Column(name = "option_code", nullable = false, columnDefinition = "char(5)")
    private String optionCode;

    @Builder
    public UserEvaluationJpaEntity(
        Long userEvaluationId,
        Long userId,
        Long perfumeId,
        String fieldCode,
        String optionCode
    ) {
        this.userEvaluationId = userEvaluationId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.fieldCode = fieldCode;
        this.optionCode = optionCode;
    }

    public static UserEvaluationJpaEntity toJpaEntity(UserEvaluation userEvaluation) {
        return UserEvaluationJpaEntity.builder()
            .userEvaluationId(userEvaluation.getUserEvaluationId())
            .userId(userEvaluation.getUserId())
            .perfumeId(userEvaluation.getPerfumeId())
            .fieldCode(userEvaluation.getField().getCode())
            .optionCode(userEvaluation.getOption().getCode())
            .build();
    }
}
