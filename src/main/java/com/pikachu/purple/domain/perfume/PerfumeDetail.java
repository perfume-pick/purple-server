package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.mainAccord.MainAccord;
import com.pikachu.purple.domain.rating.Rating;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeDetail {

    // perfume
    private Long perfumeId;
    private String perfumeName;
    private String brandName;
    private String imageUrl;

    // main accord
    private List<MainAccord> mainAccords;

    // notes
    private List<PerfumeNote> perfumeNotes;


    @Builder
    public PerfumeDetail(
            Long perfumeId,
            String perfumeName,
            String brandName,
            String imageUrl,
            List<MainAccord> mainAccords,
            List<PerfumeNote> perfumeNotes
    ) {
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.imageUrl = imageUrl;
        this.mainAccords = mainAccords;
        this.perfumeNotes = perfumeNotes;
    }

    public static PerfumeDetail of(
            Perfume perfume,
            List<MainAccord> mainAccords,
            List<PerfumeNote> perfumeNotes
    ) {
        return PerfumeDetail.builder()
                .perfumeId(perfume.getPerfumeId())
                .perfumeName(perfume.getBrandName())
                .brandName(perfume.getBrandName())
                .imageUrl(perfume.getImageUrl())
                .mainAccords(mainAccords)
                .perfumeNotes(perfumeNotes)
                .build();
    }

}
