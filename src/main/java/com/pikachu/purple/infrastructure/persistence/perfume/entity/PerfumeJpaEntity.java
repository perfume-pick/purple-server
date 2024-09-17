package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long id;

    @Column(name = "perfume_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_name")
    private BrandJpaEntity brandJpaEntity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "average_score")
    private double averageScore;

    public static Perfume toDomain(PerfumeJpaEntity jpaEntity) {
        return Perfume.builder()
            .id(jpaEntity.getId())
            .name(jpaEntity.getName())
            .brand(BrandJpaEntity.toDomain(jpaEntity.getBrandJpaEntity()))
            .imageUrl(jpaEntity.getImageUrl())
            .averageScore(jpaEntity.getAverageScore())
            .build();
    }

}
