package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.perfume.enums.NoteType;
import com.pikachu.purple.domain.perfume.enums.PerfumeNoteType;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.id.PerfumeNoteId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "perfume_note")
@IdClass(PerfumeNoteId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeNoteJpaEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @Column(name = "note_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "note_type",
        columnDefinition = "varchar(255)"
    )
    private NoteType type;

}
