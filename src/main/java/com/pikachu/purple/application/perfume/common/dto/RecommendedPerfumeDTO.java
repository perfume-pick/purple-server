package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record RecommendedPerfumeDTO(
    Long perfumeId,
    String name,
    String brandName,
    String imageUrl,
    double averageScore,
    List<String> accordNames
) {

    public static RecommendedPerfumeDTO from(
        Perfume perfume,
        List<String> accordNames
    ) {
        return new RecommendedPerfumeDTO(
            perfume.getId(),
            perfume.getName(),
            perfume.getBrand().getName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            accordNames
        );
    }

}
