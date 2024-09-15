package com.pikachu.purple.infrastructure.persistence.perfume.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "order")
    private Long order;

}
