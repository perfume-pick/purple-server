package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;

public interface PerfumeNoteRepository {

    List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeIds);

    List<PerfumeNote> findAllByPerfumeId(Long perfumeId);

}
