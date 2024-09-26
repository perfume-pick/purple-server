package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeDTO(
    String perfumeId,
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
            IdUtil.toString(perfume.getId()),
            perfume.getName(),
            perfume.getBrand().getName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            accordName
        );
    }

}
