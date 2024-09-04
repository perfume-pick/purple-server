package com.pikachu.purple.infrastructure.persistence.evaluation.entity;

import com.pikachu.purple.domain.evaluation.EvaluationMood;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "evaluation_mood")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationMoodJpaEntity {

    @Id
    @Column(name = "mood_name")
    private String moodName;

    public static EvaluationMood toDomain(EvaluationMoodJpaEntity evaluationMoodJpaEntity) {
        return EvaluationMood.builder()
            .moodName(evaluationMoodJpaEntity.getMoodName())
            .build();
    }

}
