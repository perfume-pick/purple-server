package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.Perfume;
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
    name = "perfume",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_perfume_brand_name_perfume_name",
            columnNames = {"brand_name", "perfume_name"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeJpaEntity {

    @Id
    @Column(name = "perfume_id")
    private Long perfumeId;

    @Column(name = "perfume_name", nullable = false)
    private String perfumeName;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "average_score")
    private double averageScore;

    public static Perfume toDomain(PerfumeJpaEntity perfumeJpaEntity) {
        return Perfume.builder()
            .perfumeId(perfumeJpaEntity.getPerfumeId())
            .perfumeName(perfumeJpaEntity.getPerfumeName())
            .brandName(perfumeJpaEntity.getBrandName())
            .imageUrl(perfumeJpaEntity.getImageUrl())
            .averageScore(perfumeJpaEntity.getAverageScore())
            .build();
    }

}
