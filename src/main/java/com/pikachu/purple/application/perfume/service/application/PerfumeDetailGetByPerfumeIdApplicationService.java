package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.mainAccord.service.domain.MainAccordDomainService;
import com.pikachu.purple.application.perfume.port.in.PerfumeDetailGetByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.perfume.service.domain.PerfumeNoteDomainService;
import com.pikachu.purple.domain.mainAccord.MainAccord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeDetail;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfumeDetailGetByPerfumeIdApplicationService implements
    PerfumeDetailGetByPerfumeIdUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final PerfumeNoteDomainService perfumeNoteDomainService;
    private final MainAccordDomainService mainAccordDomainService;

    private static final int MAX_SIZE = 5;

    @Override
    public Result invoke(Command command) {
        Perfume perfume = perfumeDomainService.findByPerfumeId(
            command.perfumeId()
        );
        List<MainAccord> mainAccords = mainAccordDomainService.findAllByPerfumeId(
            perfume.getPerfumeId(),
            MAX_SIZE
        );
        List<PerfumeNote> perfumeNotes = perfumeNoteDomainService.findAllByPerfumeId(
            perfume.getPerfumeId()
        );

        PerfumeDetail perfumeDetail = PerfumeDetail.of(
            perfume,
            mainAccords,
            perfumeNotes
        );

        return new Result(perfumeDetail);
    }

}
