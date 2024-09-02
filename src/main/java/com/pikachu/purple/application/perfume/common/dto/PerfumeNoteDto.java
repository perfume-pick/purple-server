package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.perfume.enums.PerfumeNoteType;

public record PerfumeNoteDto(String noteName, PerfumeNoteType perfumeNoteType) {

    public static PerfumeNoteDto from(PerfumeNote perfumeNote) {
        return new PerfumeNoteDto(
            perfumeNote.getNoteName(),
            perfumeNote.getPerfumeNoteType()
        );
    }

}
