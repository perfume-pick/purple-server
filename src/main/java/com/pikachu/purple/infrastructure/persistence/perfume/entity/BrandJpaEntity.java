package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import com.pikachu.purple.domain.perfume.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "brand")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandJpaEntity {

    @Id
    @Column(name = "brand_name")
    private String name;

    @Column(name = "brand_korean_name")
    private String koreaName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "display_order")
    private int order;

    public static Brand toDomain(BrandJpaEntity jpaEntity) {
        return new Brand(
            jpaEntity.getName(),
            jpaEntity.getKoreaName(),
            jpaEntity.getImageUrl(),
            jpaEntity.getOrder()
        );
    }

}
