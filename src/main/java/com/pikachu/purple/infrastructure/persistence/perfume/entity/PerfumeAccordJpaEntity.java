package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.infrastructure.persistence.accord.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.id.PerfumeAccordId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "perfume_accord")
@IdClass(PerfumeAccordId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeAccordJpaEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accord_name")
    private AccordJpaEntity accordJpaEntity;

    private int value;

}
