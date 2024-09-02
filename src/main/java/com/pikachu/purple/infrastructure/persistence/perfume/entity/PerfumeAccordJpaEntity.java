package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.PerfumeAccord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
    name = "main_accord",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_main_accord",
            columnNames = {"perfume_id", "note_name"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeAccordJpaEntity {

    @Id
    @Column(name = "main_accord_id")
    private Long mainAccordId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @Column(name = "accord_value", nullable = false)
    private int accordValue;

    public static PerfumeAccord toDomain(PerfumeAccordJpaEntity perfumeAccordJpaEntity) {
        return PerfumeAccord.builder()
            .mainAccordId(perfumeAccordJpaEntity.getMainAccordId())
            .perfumeId(perfumeAccordJpaEntity.getPerfumeId())
            .noteName(perfumeAccordJpaEntity.getNoteName())
            .accordValue(perfumeAccordJpaEntity.getAccordValue())
            .build();
    }

}