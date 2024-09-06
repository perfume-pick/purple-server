package com.pikachu.purple.infrastructure.persistence.evaluation.entity;

import com.pikachu.purple.domain.evaluation.EvaluationMood;
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
@Table(name = "evaluation_mood")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationMoodJpaEntity extends BaseEntity {

    @Id
    @Column(name = "evaluation_mood_id")
    private Long evaluationMoodId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "mood_name", nullable = false)
    private String moodName;

    @Builder
    public EvaluationMoodJpaEntity(
        Long evaluationMoodId,
        Long userId,
        Long perfumeId,
        String moodName
    ) {
        this.evaluationMoodId = evaluationMoodId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.moodName = moodName;
    }

    public static EvaluationMoodJpaEntity toJpaEntity(EvaluationMood evaluationMood) {
        return EvaluationMoodJpaEntity.builder()
            .evaluationMoodId(evaluationMood.getEvaluationMoodId())
            .userId(evaluationMood.getUserId())
            .perfumeId(evaluationMood.getPerfumeId())
            .moodName(evaluationMood.getMoodName())
            .build();
    }

}
