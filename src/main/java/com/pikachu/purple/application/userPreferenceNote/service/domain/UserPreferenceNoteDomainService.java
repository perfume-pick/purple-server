package com.pikachu.purple.application.userPreferenceNote.service.domain;

import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;

public interface UserPreferenceNoteDomainService {

    void save(
        List<Long> userPreferenceNoteIdList,
        Long userId,
        List<Note> perfumeNoteList
    );

    List<UserPreferenceNote> getByUserId(Long userId);

}
