package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.perfume.enums.PerfumeNoteType;

public record PerfumeNoteDTO(
    String noteName,
    PerfumeNoteType perfumeNoteType
) {

    public static PerfumeNoteDTO from(PerfumeNote perfumeNote) {
        return new PerfumeNoteDTO(
            perfumeNote.getNoteName(),
            perfumeNote.getPerfumeNoteType()
        );
    }

}
