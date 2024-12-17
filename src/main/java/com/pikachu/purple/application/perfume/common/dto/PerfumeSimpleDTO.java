package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeSimpleDTO(
    String perfumeId,
    String name,
    String imageUrl,
    String brandName
) {

    public static PerfumeSimpleDTO from(Perfume perfume) {
        return new PerfumeSimpleDTO(
            IdUtil.toString(perfume.getId()),
            perfume.getKoreanName(),
            perfume.getImageUrl(),
            perfume.getBrand().getKoreanName()
        );
    }

}
