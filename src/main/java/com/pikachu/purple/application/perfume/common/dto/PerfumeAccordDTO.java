package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

public record PerfumeAccordDTO(
    int order,
    String noteName,
    int accordValue
) {

    public static PerfumeAccordDTO of(
        int order,
        PerfumeAccord perfumeAccord
    ) {
        return new PerfumeAccordDTO(
            order,
            perfumeAccord.getNoteName(),
            perfumeAccord.getAccordValue()
        );
    }

}
