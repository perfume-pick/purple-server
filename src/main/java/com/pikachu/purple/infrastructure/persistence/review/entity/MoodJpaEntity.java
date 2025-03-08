package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Mood;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "mood")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MoodJpaEntity {

    @Id
    @Column(name = "mood_name")
    private String name;

    public static Mood toDomain(MoodJpaEntity jpaEntity) {
        return new Mood(jpaEntity.getName());
    }

}
