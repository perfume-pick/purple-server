package com.pikachu.purple.bootstrap.perfume.dto.response;

import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDTO;
import com.pikachu.purple.domain.perfume.Note;
import java.util.List;

public record GetAccordsAndNotesResponse(
    List<PerfumeAccordDTO> accords,
    List<Note> notes
) {}
