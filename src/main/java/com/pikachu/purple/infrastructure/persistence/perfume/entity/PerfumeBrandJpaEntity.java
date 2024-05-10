package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.PerfumeBrand;
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
@Table(name = "perfume_brand")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeBrandJpaEntity {

    @Id
    @Column(name = "Brand_name")
    private String brandName;

    @Builder
    public PerfumeBrandJpaEntity(String brandName) {
        this.brandName = brandName;
    }

    public static PerfumeBrand toDomain(PerfumeBrandJpaEntity perfumeBrandEntity) {
        return PerfumeBrand.builder()
            .brandName(perfumeBrandEntity.brandName)
            .build();
    }

    public static PerfumeBrandJpaEntity toJpaEntity(PerfumeBrand perfumeBrand) {
        return PerfumeBrandJpaEntity.builder()
            .brandName(perfumeBrand.getBrandName())
            .build();
    }

}
