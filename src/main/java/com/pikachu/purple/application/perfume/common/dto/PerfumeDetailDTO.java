package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;

public record PerfumeDetailDTO(
    String perfumeName,
    String brandName,
    String imageUrl,
    List<PerfumeAccordDTO> perfumeAccords,
    List<PerfumeNoteDTO> perfumeNotes
) {

    public static PerfumeDetailDTO of(
        Perfume perfume,
        List<PerfumeAccordDTO> perfumeAccords,
        List<PerfumeNoteDTO> perfumeNotes
    ) {
        return new PerfumeDetailDTO(
            perfume.getPerfumeName(),
            perfume.getBrandName(),
            perfume.getImageUrl(),
            perfumeAccords,
            perfumeNotes
        );
    }

}
