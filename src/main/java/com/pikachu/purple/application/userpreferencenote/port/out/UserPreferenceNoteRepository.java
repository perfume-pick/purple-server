package com.pikachu.purple.application.userpreferencenote.port.out;

import com.pikachu.purple.domain.user.UserPreferenceNote;
import java.util.List;

public interface UserPreferenceNoteRepository {

    void save(List<UserPreferenceNote> userPreferenceNotes);

    List<UserPreferenceNote> getAllByUserId(Long userId);

}
