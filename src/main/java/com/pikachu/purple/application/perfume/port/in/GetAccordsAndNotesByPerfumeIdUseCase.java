package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDTO;
import com.pikachu.purple.domain.perfume.Note;
import java.util.List;

public interface GetAccordsAndNotesByPerfumeIdUseCase {

    GetAccordsAndNotesByPerfumeIdUseCase.Result invoke(
        GetAccordsAndNotesByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {}

    record Result(
        List<PerfumeAccordDTO> accords,
        List<Note> notes
    ) {}

}
