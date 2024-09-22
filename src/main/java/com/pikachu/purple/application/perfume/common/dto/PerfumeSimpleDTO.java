package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeSimpleDTO(
    Long perfumeId,
    String name,
    String imageUrl,
    String brandName
) {

    public static PerfumeSimpleDTO from(Perfume perfume) {
        return new PerfumeSimpleDTO(
            perfume.getId(),
            perfume.getName(),
            perfume.getImageUrl(),
            perfume.getBrand().getName()
        );
    }

}
