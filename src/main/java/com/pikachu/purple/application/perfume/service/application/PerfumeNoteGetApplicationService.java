package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetUseCase;
import com.pikachu.purple.application.perfume.service.domain.NoteDomainService;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeNoteGetApplicationService implements
    PerfumeNoteGetUseCase {

    private final NoteDomainService noteDomainService;

    @Override
    public List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeIds) {
        return noteDomainService.getAllByPerfumeIds(perfumeIds);
    }

}
