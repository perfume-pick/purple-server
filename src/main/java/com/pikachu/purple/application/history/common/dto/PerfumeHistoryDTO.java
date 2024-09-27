package com.pikachu.purple.application.history.common.dto;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;

public record PerfumeHistoryDTO(
    int order,
    PerfumeDTO perfume
) {

    public static PerfumeHistoryDTO of(
        int order,
        PerfumeDTO perfume
    ) {
        return new PerfumeHistoryDTO(
            order,
            perfume
        );
    }

}
