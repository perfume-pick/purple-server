package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDto;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDto;
import com.pikachu.purple.application.perfume.common.dto.PerfumeNoteDto;
import com.pikachu.purple.application.perfume.service.domain.PerfumeAccordDomainService;
import com.pikachu.purple.application.perfume.port.in.PerfumeDetailGetByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.perfume.service.domain.PerfumeNoteDomainService;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.perfume.Perfume;
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
    private final PerfumeAccordDomainService perfumeAccordDomainService;

    private static final int MAX_SIZE = 5;

    @Override
    public Result invoke(Command command) {
        Perfume perfume = perfumeDomainService.findByPerfumeId(
            command.perfumeId()
        );

        List<PerfumeAccord> perfumeAccords = perfumeAccordDomainService.findAllByPerfumeId(
            perfume.getPerfumeId(),
            MAX_SIZE
        );
        List<PerfumeAccordDto> perfumeAccordDtos = perfumeAccords.stream()
            .map(PerfumeAccordDto::from).toList();

        List<PerfumeNote> perfumeNotes = perfumeNoteDomainService.findAllByPerfumeId(
            perfume.getPerfumeId()
        );
        List<PerfumeNoteDto> perfumeNoteDtos = perfumeNotes.stream()
            .map(PerfumeNoteDto::from).toList();

        PerfumeDetailDto perfumeDetailDto = PerfumeDetailDto.of(
            perfume,
            perfumeAccordDtos,
            perfumeNoteDtos
        );

        return new Result(perfumeDetailDto);
    }

}