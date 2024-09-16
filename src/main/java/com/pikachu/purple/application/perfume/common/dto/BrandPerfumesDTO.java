package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Brand;
import java.util.List;

public record BrandPerfumesDTO(
    String brandName,
    List<PerfumeSimpleDTO> perfumeSimpleDTOs
) {

    public static BrandPerfumesDTO from(Brand brand) {
        return new BrandPerfumesDTO(
            brand.getName(),
            brand.getPerfumes().stream()
                .map(PerfumeSimpleDTO::from)
                .toList()
        );
    }

}
