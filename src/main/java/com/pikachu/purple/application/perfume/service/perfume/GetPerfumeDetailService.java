package com.pikachu.purple.application.perfume.service.perfume;

import com.pikachu.purple.application.perfume.common.dto.PerfumeAccordDTO;
import com.pikachu.purple.application.perfume.common.dto.PerfumeDetailDTO;
import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeDetailUseCase;
import com.pikachu.purple.application.perfume.port.out.NoteRepository;
import com.pikachu.purple.application.perfume.port.out.PerfumeAccordRepository;
import com.pikachu.purple.application.perfume.port.out.PerfumeRepository;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.perfume.PerfumeAccord;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetPerfumeDetailService implements
    GetPerfumeDetailUseCase {

    private final PerfumeRepository perfumeRepository;
    private final NoteRepository noteRepository;
    private final PerfumeAccordRepository perfumeAccordRepository;

    private static final int MAX_SIZE = 5;

    @Override
    public Result find(Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId);

        List<PerfumeAccord> perfumeAccords = perfumeAccordRepository
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

        List<Note> notes = noteRepository.findAllByPerfumeId(
            perfume.getId());

        return new Result(PerfumeDetailDTO.of(
            perfume,
            perfumeAccordDTOs,
            notes
        ));
    }

}
