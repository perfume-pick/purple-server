package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.perfume.enums.PerfumeNoteType;
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
    private PerfumeNoteType perfumeNoteType;

    @Builder
    public PerfumeNote(
        Long perfumeNoteId,
        Long perfumeId,
        String noteName,
        PerfumeNoteType perfumeNoteType
    ) {
        this.perfumeNoteId = perfumeNoteId;
        this.perfumeId = perfumeId;
        this.noteName = noteName;
        this.perfumeNoteType = perfumeNoteType;
    }

}
