package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetByPerfumeIdsUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeNoteDomainService;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeNoteGetByPerfumeIdsApplicationService implements
    PerfumeNoteGetByPerfumeIdsUseCase {

    private final PerfumeNoteDomainService perfumeNoteDomainService;

    @Override
    public Result invoke(List<Long> perfumeIds) {
        List<PerfumeNote> perfumeNotes = perfumeNoteDomainService.getAllByPerfumeIds(perfumeIds);

        return new Result(perfumeNotes);
    }

}
