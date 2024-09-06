package com.pikachu.purple.infrastructure.persistence.mood.entity;

import com.pikachu.purple.domain.mood.Mood;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "mood")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoodJpaEntity {

    @Id
    @Column(name = "mood_name")
    private String moodName;

    public static Mood toDomain(MoodJpaEntity moodJpaEntity) {
        return Mood.builder()
            .moodName(moodJpaEntity.getMoodName())
            .build();
    }

}
