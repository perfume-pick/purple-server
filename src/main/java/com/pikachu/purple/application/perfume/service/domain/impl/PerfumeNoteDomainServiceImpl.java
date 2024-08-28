package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.PerfumeNoteRepository;
import com.pikachu.purple.application.perfume.service.domain.PerfumeNoteDomainService;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeNoteDomainServiceImpl implements PerfumeNoteDomainService {

    private final PerfumeNoteRepository perfumeNoteRepository;

    @Override
    public List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeIds) {
        return perfumeNoteRepository.getAllByPerfumeIds(perfumeIds);
    }

    @Override
    public List<PerfumeNote> findAllByPerfumeId(Long perfumeId) {
        return perfumeNoteRepository.findAllByPerfumeId(perfumeId);
    }

}
