package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.port.in.PerfumeGetByUserPreferenceNoteUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeGetByUserPreferenceNoteApplicationService implements PerfumeGetByUserPreferenceNoteUseCase {

    private final PerfumeDomainService perfumeDomainService;

    @Override
    public Result invoke() {
        List<Perfume> perfumeList = perfumeDomainService.findByUserPreferenceNotes(1L);

        return new Result(perfumeList);
    }

}
