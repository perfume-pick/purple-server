package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Brand;

public record BrandDTO(
    String name,
    String imageUrl,
    int order
) {

    public static BrandDTO from(Brand brand) {
        return new BrandDTO(
            brand.getName(),
            brand.getImageUrl(),
            brand.getOrder()
        );
    }

}
