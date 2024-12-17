package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record RecommendedPerfumeDTO(
    String perfumeId,
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
            IdUtil.toString(perfume.getId()),
            perfume.getKoreanName(),
            perfume.getBrand().getKoreanName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            accordNames
        );
    }

}
