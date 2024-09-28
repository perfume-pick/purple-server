package com.pikachu.purple.application.history.common.dto;

import com.pikachu.purple.application.perfume.common.dto.PerfumeDTO;

public record VisitHistoryDTO(
    int order,
    PerfumeDTO perfume
) {

    public static VisitHistoryDTO of(
        int order,
        PerfumeDTO perfume
    ) {
        return new VisitHistoryDTO(
            order,
            perfume
        );
    }

}
