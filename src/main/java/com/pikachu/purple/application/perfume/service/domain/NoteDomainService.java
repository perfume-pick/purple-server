package com.pikachu.purple.application.perfume.service.domain;

import com.pikachu.purple.domain.perfume.Note;
import java.util.List;

public interface NoteDomainService {

    List<Note> findAllByPerfumeIds(List<Long> perfumeIds);

    List<Note> findAllByPerfumeId(Long perfumeId);

}
