package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeDTO(
    Long perfumeId,
    String perfumeName,
    String brandName,
    String imageUrl,
    double averageScore,
    String accordName
) {

    public static PerfumeDTO from(
        Perfume perfume,
        String accordName
    ) {
        return new PerfumeDTO(
            perfume.getId(),
            perfume.getName(),
            perfume.getBrand().getName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            accordName
        );
    }

}
