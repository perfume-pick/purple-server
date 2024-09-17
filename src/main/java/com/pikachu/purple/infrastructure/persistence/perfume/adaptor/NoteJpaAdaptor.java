package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.NoteRepository;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.NoteJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.NoteJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteJpaAdaptor implements NoteRepository {

    private final NoteJpaRepository noteJpaRepository;

    @Override
    public List<Note> findAllByPerfumeId(Long perfumeId) {
        List<NoteJpaEntity> perfumeNoteJpaEntities = noteJpaRepository.findAllByPerfumeId(
            perfumeId);

        return perfumeNoteJpaEntities.stream()
            .map(NoteJpaEntity::toDomain)
            .toList();
    }

}
