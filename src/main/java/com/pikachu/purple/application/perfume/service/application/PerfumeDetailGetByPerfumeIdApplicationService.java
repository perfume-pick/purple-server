package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDTO;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDTO;
import com.pikachu.purple.application.perfume.common.dto.PerfumeNoteDTO;
import com.pikachu.purple.application.perfume.service.domain.PerfumeAccordDomainService;
import com.pikachu.purple.application.perfume.port.in.PerfumeDetailGetByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.perfume.service.domain.PerfumeNoteDomainService;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.ArrayList;
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
        Perfume perfume = perfumeDomainService.findByPerfumeId(command.perfumeId());

        List<PerfumeAccord> perfumeAccords = perfumeAccordDomainService
            .findAllByPerfumeIdOrderByAccordValueDesc(
                perfume.getPerfumeId(),
                MAX_SIZE
            );
        List<PerfumeAccordDTO> perfumeAccordDTOs = new ArrayList<>();
        for (int i = 0; i < perfumeAccords.size(); i++) {
            int order = i + 1;
            perfumeAccordDTOs.add(
                PerfumeAccordDTO.of(
                    order,
                    perfumeAccords.get(i)
                )
            );
        }

        List<PerfumeNote> perfumeNotes = perfumeNoteDomainService.findAllByPerfumeId(
            perfume.getPerfumeId());
        List<PerfumeNoteDTO> perfumeNoteDTOs = perfumeNotes.stream()
            .map(PerfumeNoteDTO::from).toList();

        PerfumeDetailDTO perfumeDetailDTO = PerfumeDetailDTO.of(
            perfume,
            perfumeAccordDTOs,
            perfumeNoteDTOs
        );

        return new Result(perfumeDetailDTO);
    }

}
