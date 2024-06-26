package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;

public interface PerfumeNoteGetByPerfumeIdsUseCase {

    Result invoke(List<Long> perfumeIds);

    record Result(List<PerfumeNote> perfumeNotes) {

    }

}
