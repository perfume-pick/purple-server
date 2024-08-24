package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.perfume.enums.PerfumeNoteType;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "note_name")
    private String noteName;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfume_note_type", columnDefinition = "varchar(255)")
    private PerfumeNoteType perfumeNoteType;

    public static PerfumeNote toDomain(PerfumeNoteJpaEntity perfumeNoteJpaEntity) {
        return PerfumeNote.builder()
                .perfumeNoteId(perfumeNoteJpaEntity.getPerfumeNoteId())
                .perfumeId(perfumeNoteJpaEntity.getPerfumeId())
                .noteName(perfumeNoteJpaEntity.getNoteName())
                .perfumeNoteType(perfumeNoteJpaEntity.getPerfumeNoteType())
                .build();
    }

}
