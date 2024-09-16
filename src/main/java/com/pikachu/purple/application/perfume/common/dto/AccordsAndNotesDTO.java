package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.perfume.Note;
import java.util.List;

public record AccordsAndNotesDTO(
    List<PerfumeAccordDTO> accords,
    List<Note> notes
) {

    public static AccordsAndNotesDTO of(
        List<PerfumeAccordDTO> perfumeAccords,
        List<Note> perfumeNotes
    ) {
        return new AccordsAndNotesDTO(
            perfumeAccords,
            perfumeNotes
        );
    }

}
