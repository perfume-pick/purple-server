package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.application.perfume.common.dto.AccordsAndNotesDTO;

public interface GetAccordsAndNotesByPerfumeIdUseCase {

    GetAccordsAndNotesByPerfumeIdUseCase.Result invoke(
        GetAccordsAndNotesByPerfumeIdUseCase.Command command);

    record Command(Long perfumeId) {}

    record Result(AccordsAndNotesDTO perfumeDetail) {}

}
