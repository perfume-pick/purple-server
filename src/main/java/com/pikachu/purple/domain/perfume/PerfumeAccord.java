package com.pikachu.purple.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeAccord {

    private Long perfumeAccordId;
    private Long perfumeId;
    private String noteName;
    private int accordValue;

    @Builder
    public PerfumeAccord(
        Long perfumeAccordId,
        Long perfumeId,
        String noteName,
        Integer accordValue
    ) {
        this.perfumeAccordId = perfumeAccordId;
        this.perfumeId = perfumeId;
        this.noteName = noteName;
        this.accordValue = accordValue;
    }

}
