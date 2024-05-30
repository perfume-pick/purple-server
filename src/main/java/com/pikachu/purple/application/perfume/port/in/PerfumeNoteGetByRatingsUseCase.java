package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;

public interface PerfumeNoteGetByRatingsUseCase {

    Result invoke(List<Long> perfumeIdList);

    record Result(List<PerfumeNote> perfumeNoteList){

    }

}
