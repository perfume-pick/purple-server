package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "perfume_note")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeNoteJpaEntity {

    @Id
    @Column(name = "perfume_note_id")
    private Long perfumeNoteId;

    @Column(name = "perfume_id")
    private Long perfumeId;

    @Column(name = "noteName")
    private String noteName;

    public static PerfumeNote toDomain(PerfumeNoteJpaEntity perfumeNoteJpaEntity) {
        return PerfumeNote.builder()
            .perfumeNoteId(perfumeNoteJpaEntity.getPerfumeNoteId())
            .perfumeId(perfumeNoteJpaEntity.getPerfumeId())
            .noteName(perfumeNoteJpaEntity.getNoteName())
            .build();
    }

}
