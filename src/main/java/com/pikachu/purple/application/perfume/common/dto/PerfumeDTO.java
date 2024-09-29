package com.pikachu.purple.application.perfume.common.dto;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeAccordNotFoundException;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.accord.Accord;
import com.pikachu.purple.domain.perfume.Perfume;

public record PerfumeDTO(
    String perfumeId,
    String perfumeName,
    String brandName,
    String imageUrl,
    double averageScore,
    String accordName
) {

    public static PerfumeDTO from(Perfume perfume) {
        return new PerfumeDTO(
            IdUtil.toString(perfume.getId()),
            perfume.getName(),
            perfume.getBrand().getName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            perfume.getAccords().stream()
                .map(Accord::getName)
                .findFirst()
                .orElseThrow(() -> PerfumeAccordNotFoundException)
        );
    }

}
