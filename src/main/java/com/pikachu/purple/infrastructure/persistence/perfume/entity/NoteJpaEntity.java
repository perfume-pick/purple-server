package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.perfume.enums.NoteType;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.id.PerfumeNoteId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "note")
@IdClass(PerfumeNoteId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoteJpaEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @Column(name = "note_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "note_type",
        columnDefinition = "varchar(255)",
        nullable = false
    )
    private NoteType type;

    public static Note toDomain(NoteJpaEntity jpaEntity) {
        return new Note(
            jpaEntity.getName(),
            jpaEntity.getType()
        );
    }

}
