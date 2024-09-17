package com.pikachu.purple.application.perfume.service.domain.impl;

import com.pikachu.purple.application.perfume.port.out.NoteRepository;
import com.pikachu.purple.application.perfume.service.domain.NoteDomainService;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteDomainServiceImpl implements NoteDomainService {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> findAllByPerfumeId(Long perfumeId) {
        return noteRepository.findAllByPerfumeId(perfumeId);
    }

}
