package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.PerfumeAccord;

public record PerfumeAccordDto(String noteName, int accordValue) {

    public static PerfumeAccordDto from(PerfumeAccord perfumeAccord) {
        return new PerfumeAccordDto(
            perfumeAccord.getNoteName(),
            perfumeAccord.getAccordValue()
        );
    }

}
