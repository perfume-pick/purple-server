package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

public record PerfumeAccordDto(
    int order,
    String noteName,
    int accordValue
) {

    public static PerfumeAccordDto of(int order, PerfumeAccord perfumeAccord) {
        return new PerfumeAccordDto(
            order,
            perfumeAccord.getNoteName(),
            perfumeAccord.getAccordValue()
        );
    }

}
