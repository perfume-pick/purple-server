package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeDTO(
    String brandName,
    String perfumeName,
    String imageUrl,
    double averageScore,
    String topAccordName
) {

    public static PerfumeDTO of(
        Perfume perfume
    ) {
        return new PerfumeDTO(
            perfume.getBrandName(),
            perfume.getPerfumeName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            "sample" //TODO 긴급 수정 요망
        );
    }

}
