package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

public record PerfumeAccordDTO(
    int order,
    String accordName,
    String accordKoreanName,
    int accordValue
) {

    public static PerfumeAccordDTO of(
        int order,
        PerfumeAccord perfumeAccord
    ) {
        return new PerfumeAccordDTO(
            order,
            perfumeAccord.getAccord().name().toLowerCase().replace("_", " "),
            perfumeAccord.getAccord().getKoreanName(),
            perfumeAccord.getValue()
        );
    }

}
