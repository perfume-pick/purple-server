package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;

public interface PerfumeNoteGetByPerfumeIdListUseCase {

    Result invoke(List<Long> perfumeIdList);

    record Result(List<PerfumeNote> perfumeNoteList){

    }

}
