package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.PerfumeNoteRepository;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeNoteJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeNoteJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeNoteJpaEntityAdaptor implements PerfumeNoteRepository {

    private final PerfumeNoteJpaRepository perfumeNoteJpaRepository;

    @Override
    public List<PerfumeNote> getAllByPerfumeIds(List<Long> perfumeIdList) {
        List<PerfumeNoteJpaEntity> perfumeNoteJpaEntityList = perfumeNoteJpaRepository.findByPerfumeIdIn(perfumeIdList);

        return perfumeNoteJpaEntityList.stream()
            .map(PerfumeNoteJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
