package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record BrandPerfumesDTO(
    String brandName,
    List<Perfume> perfumes
) {

    public static BrandPerfumesDTO of(
        String brandName,
        List<Perfume> perfumes
    ) {
        return new BrandPerfumesDTO(
            brandName,
            perfumes
        );
    }

}