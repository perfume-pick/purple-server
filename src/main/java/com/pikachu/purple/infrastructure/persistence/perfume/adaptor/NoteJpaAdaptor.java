package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.NoteRepository;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeNoteJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeNoteJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteJpaAdaptor implements NoteRepository {

    private final PerfumeNoteJpaRepository perfumeNoteJpaRepository;

    @Override
    public List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeIds) {
        List<PerfumeNoteJpaEntity> perfumeNoteJpaEntities = perfumeNoteJpaRepository.findByPerfumeIdIn(
            perfumeIds);

        return perfumeNoteJpaEntities.stream()
            .map(PerfumeNoteJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<PerfumeNote> findAllByPerfumeId(Long perfumeId) {
        List<PerfumeNoteJpaEntity> perfumeNoteJpaEntities = perfumeNoteJpaRepository.findAllByPerfumeId(
            perfumeId);

        return perfumeNoteJpaEntities.stream()
            .map(PerfumeNoteJpaEntity::toDomain)
            .toList();
    }

}
