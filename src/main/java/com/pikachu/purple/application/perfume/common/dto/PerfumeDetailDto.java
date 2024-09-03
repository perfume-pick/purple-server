package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record PerfumeDetailDto(
    String perfumeName,
    String brandName,
    String imageUrl,
    List<PerfumeAccordDto> perfumeAccords,
    List<PerfumeNoteDto> perfumeNotes
) {

    public static PerfumeDetailDto of(
        Perfume perfume,
        List<PerfumeAccordDto> perfumeAccords,
        List<PerfumeNoteDto> perfumeNotes
    ) {
        return new PerfumeDetailDto(
            perfume.getPerfumeName(),
            perfume.getBrandName(),
            perfume.getImageUrl(),
            perfumeAccords,
            perfumeNotes
        );
    }

}
