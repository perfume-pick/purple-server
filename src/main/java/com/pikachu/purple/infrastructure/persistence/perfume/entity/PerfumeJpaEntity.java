package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "perfume")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeJpaEntity {

    @Id
    @Column(name = "perfume_id")
    private Long perfumeId;

    @Column(name = "perfume_name", nullable = false)
    private String perfumeName;

    @Column(name = "p_brand_name", nullable = false)
    private String brandName;

    public static Perfume toDomain(PerfumeJpaEntity perfumeJpaEntity) {
        return Perfume.builder()
            .perfumeId(perfumeJpaEntity.getPerfumeId())
            .perfumeName(perfumeJpaEntity.getPerfumeName())
            .brandName(perfumeJpaEntity.getBrandName())
            .build();
    }

}
