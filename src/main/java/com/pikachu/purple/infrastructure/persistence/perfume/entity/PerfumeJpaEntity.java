package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.Perfume;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "perfume")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeJpaEntity {

    @Id
    @Column(name = "perfume_id")
    private Long id;

    @Column(name = "perfume_name", nullable = false)
    private String name;

    @Column(name = "perfume_korean_name", nullable = false)
    private String koreanName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_name")
    private BrandJpaEntity brandJpaEntity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "average_score")
    private double averageScore;

    public static Perfume toDomain(PerfumeJpaEntity jpaEntity) {
        return new Perfume(
            jpaEntity.getId(),
            jpaEntity.getName(),
            jpaEntity.getKoreanName(),
            jpaEntity.getImageUrl(),
            jpaEntity.getAverageScore(),
            BrandJpaEntity.toDomain(jpaEntity.getBrandJpaEntity())
        );
    }

    public static Perfume toDummy(PerfumeJpaEntity jpaEntity) {
        return Perfume.builder()
            .id(jpaEntity.getId())
            .build();
    }

}
