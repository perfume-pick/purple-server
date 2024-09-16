package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeDTO(
    String name,
    String imageUrl,
    double averageScore,
    String accordName
) {

    public static PerfumeDTO from(
        Perfume perfume,
        String accordName
    ) {
        return new PerfumeDTO(
            perfume.getName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            accordName
        );
    }

}
