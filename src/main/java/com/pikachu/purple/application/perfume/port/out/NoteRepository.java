package com.pikachu.purple.application.perfume.port.out;

import com.pikachu.purple.domain.perfume.Note;
import java.util.List;

public interface NoteRepository {

    List<Note> getAllByPerfumeIds(List<Long> perfumeIds);

    List<Note> findAllByPerfumeId(Long perfumeId);

}
