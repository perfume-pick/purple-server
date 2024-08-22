package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;

public interface PerfumeNoteGetUseCase {

    List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeIds);

}
