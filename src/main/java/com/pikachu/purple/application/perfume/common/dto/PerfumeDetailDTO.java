package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.util.MathUtil;
import java.util.List;

public record PerfumeDetailDTO(
    String perfumeId,
    String perfumeName,
    String brandName,
    String imageUrl,
    double averageScore,
    List<PerfumeAccordDTO> accords,
    List<Note> notes
) {

    public static PerfumeDetailDTO of(
        Perfume perfume,
        List<PerfumeAccordDTO> accords,
        List<Note> notes
    ) {
        return new PerfumeDetailDTO(
            IdUtil.toString(perfume.getId()),
            perfume.getKoreanName(),
            perfume.getBrand().getKoreanName(),
            perfume.getImageUrl(),
            MathUtil.round(perfume.getAverageScore(), 1),
            accords,
            notes
        );
    }

}
