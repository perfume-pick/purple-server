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
    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "display_order")
    private Long displayOrder;

    @Builder
    public PerfumeBrandJpaEntity(
        String brandName,
        String imageUrl
    ) {
        this.brandName = brandName;
        this.imageUrl = imageUrl;
    }

    public static PerfumeBrand toDomain(PerfumeBrandJpaEntity perfumeBrandEntity) {
        return PerfumeBrand.builder()
            .brandName(perfumeBrandEntity.brandName)
            .imageUrl(perfumeBrandEntity.imageUrl)
            .build();
    }

    public static PerfumeBrandJpaEntity toJpaEntity(PerfumeBrand perfumeBrand) {
        return PerfumeBrandJpaEntity.builder()
            .brandName(perfumeBrand.getBrandName())
            .imageUrl(perfumeBrand.getImageUrl())
            .build();
    }

}
