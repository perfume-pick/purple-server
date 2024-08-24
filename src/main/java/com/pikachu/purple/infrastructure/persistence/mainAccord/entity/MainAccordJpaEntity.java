package com.pikachu.purple.infrastructure.persistence.mainAccord.entity;

import com.pikachu.purple.domain.mainAccord.MainAccord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "main_accord")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainAccordJpaEntity {
    @Id
    @Column(name = "main_accord_id")
    private Long mainAccordId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @Column(name = "accord_value", nullable = false)
    private int accordValue;

    public static MainAccord toDomain(MainAccordJpaEntity mainAccordJpaEntity) {
        return MainAccord.builder()
                .mainAccordId(mainAccordJpaEntity.getMainAccordId())
                .perfumeId(mainAccordJpaEntity.getPerfumeId())
                .noteName(mainAccordJpaEntity.getNoteName())
                .accordValue(mainAccordJpaEntity.getAccordValue())
                .build();
    }
}
