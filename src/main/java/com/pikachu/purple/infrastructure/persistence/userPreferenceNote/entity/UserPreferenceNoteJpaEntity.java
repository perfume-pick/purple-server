package com.pikachu.purple.infrastructure.persistence.userPreferenceNote.entity;

import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
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
@Table(name = "user_preference_note")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPreferenceNoteJpaEntity {

    @Id
    @Column(name = "user_preference_note_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @Builder
    public UserPreferenceNoteJpaEntity(
        Long id,
        Long userId,
        String noteName
    ) {
        this.id = id;
        this.userId = userId;
        this.noteName = noteName;
    }

    public static UserPreferenceNoteJpaEntity toJpaEntity(UserPreferenceNote userPreferenceNote){
        return UserPreferenceNoteJpaEntity.builder()
            .id(userPreferenceNote.getId())
            .userId(userPreferenceNote.getUserId())
            .noteName(userPreferenceNote.getNoteName())
            .build();
    }

    public static UserPreferenceNote toDomain(UserPreferenceNoteJpaEntity userPreferenceNoteJpaEntity){
        return UserPreferenceNote.builder()
            .id(userPreferenceNoteJpaEntity.getId())
            .userId(userPreferenceNoteJpaEntity.getUserId())
            .noteName(userPreferenceNoteJpaEntity.getNoteName())
            .build();
    }

}
