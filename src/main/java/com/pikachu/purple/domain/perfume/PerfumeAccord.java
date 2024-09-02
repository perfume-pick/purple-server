package com.pikachu.purple.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeAccord {

    private Long mainAccordId;
    private Long perfumeId;
    private String noteName;
    private int accordValue;

    @Builder
    public PerfumeAccord(
        Long mainAccordId,
        Long perfumeId,
        String noteName,
        Integer accordValue
    ) {
        this.mainAccordId = mainAccordId;
        this.perfumeId = perfumeId;
        this.noteName = noteName;
        this.accordValue = accordValue;
    }

}
