package com.pikachu.purple.domain.perfume;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerfumeNote {

    private Long perfumeNoteId;
    private Long perfumeId;
    private String noteName;

    @Builder
    public PerfumeNote(
        Long perfumeNoteId,
        Long perfumeId,
        String noteName
    ) {
        this.perfumeNoteId = perfumeNoteId;
        this.perfumeId = perfumeId;
        this.noteName = noteName;
    }

}
