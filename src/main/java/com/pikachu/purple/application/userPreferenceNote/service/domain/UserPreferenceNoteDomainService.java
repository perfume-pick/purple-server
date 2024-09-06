package com.pikachu.purple.application.userPreferenceNote.service.domain;

import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;

public interface UserPreferenceNoteDomainService {

    void save(
        Long userId,
        List<Note> perfumeNotes
    );

    List<UserPreferenceNote> getAllByUserId(Long userId);

}
