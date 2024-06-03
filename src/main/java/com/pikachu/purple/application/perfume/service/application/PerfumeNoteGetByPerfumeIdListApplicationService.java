package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetByPerfumeIdListUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeNoteDomainService;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeNoteGetByPerfumeIdListApplicationService implements
    PerfumeNoteGetByPerfumeIdListUseCase {

    private final PerfumeNoteDomainService perfumeNoteDomainService;

    @Override
    public Result invoke(List<Long> perfumeIdList) {
        List<PerfumeNote> perfumeNoteList = perfumeNoteDomainService.getByPerfumeIdList(perfumeIdList);

        return new Result(perfumeNoteList);
    }

}
