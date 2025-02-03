package com.pikachu.purple.application.perfume.service.application.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDTO;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeDetailUseCase;
import com.pikachu.purple.application.perfume.service.domain.NoteDomainService;
import com.pikachu.purple.application.perfume.service.domain.PerfumeAccordDomainService;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumeDetailService implements
    GetPerfumeDetailUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final NoteDomainService noteDomainService;
    private final PerfumeAccordDomainService perfumeAccordDomainService;

    private static final int MAX_SIZE = 5;

    @Override
    public Result findWithPerfumeAccordsAndNotes(Long perfumeId) {
        Perfume perfume = perfumeDomainService.findById(perfumeId);

        List<PerfumeAccord> perfumeAccords = perfumeAccordDomainService
            .findAllByPerfumeIdOrderByValueDesc(
                perfume.getId(),
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
            perfume.getId());

        return new Result(PerfumeDetailDTO.of(
            perfume,
            perfumeAccordDTOs,
            notes
        ));
    }

}
