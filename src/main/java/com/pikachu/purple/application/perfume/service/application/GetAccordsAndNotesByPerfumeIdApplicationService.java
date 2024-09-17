package com.pikachu.purple.application.perfume.service.application;

import com.pikachu.purple.application.perfume.common.dto.AccordsAndNotesDTO;
import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDTO;
import com.pikachu.purple.application.perfume.port.in.GetAccordsAndNotesByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeAccordDomainService;
import com.pikachu.purple.application.perfume.service.domain.NoteDomainService;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccordsAndNotesByPerfumeIdApplicationService implements
    GetAccordsAndNotesByPerfumeIdUseCase {

    private final NoteDomainService noteDomainService;
    private final PerfumeAccordDomainService perfumeAccordDomainService;

    private static final int MAX_SIZE = 5;

    @Override
    public Result invoke(Command command) {

        List<PerfumeAccord> perfumeAccords = perfumeAccordDomainService
            .findAllByPerfumeIdOrderByValueDesc(
                command.perfumeId(),
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


        List<Note> notes = noteDomainService.findAllByPerfumeId(
            command.perfumeId());

        AccordsAndNotesDTO accordsAndNotesDTO = AccordsAndNotesDTO.of(
            perfumeAccordDTOs,
            notes
        );

        return new Result(accordsAndNotesDTO);
    }

}
