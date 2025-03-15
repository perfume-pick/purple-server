package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "perfume_accord")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeAccordJpaEntity {

    @Id
    @Column(name = "perfume_id")
    private Long perfumeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "accord_name")
    private Accord accord;


    @Column(name = "value")
    private int value;

    public static PerfumeAccord toDomain(PerfumeAccordJpaEntity jpaEntity) {
        return new PerfumeAccord(
            jpaEntity.getAccord(),
            jpaEntity.getValue()
        );
    }

}
