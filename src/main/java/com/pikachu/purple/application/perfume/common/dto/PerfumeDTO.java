package com.pikachu.purple.application.perfume.common.dto;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeAccordNotFoundException;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;

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
            perfume.getKoreanName(),
            perfume.getBrand().getKoreanName(),
            perfume.getImageUrl(),
            perfume.getAverageScore(),
            perfume.getAccords().stream()
                .map(PerfumeAccord::getAccord)
                .map(Accord::getKoreanName)
                .findFirst()
                .orElseThrow(() -> PerfumeAccordNotFoundException)
        );
    }

}
