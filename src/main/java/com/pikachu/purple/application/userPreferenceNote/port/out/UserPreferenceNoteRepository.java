package com.pikachu.purple.application.userPreferenceNote.port.out;

import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;

public interface UserPreferenceNoteRepository {

    void save(List<UserPreferenceNote> userPreferenceNoteList);

    List<UserPreferenceNote> getByUserId(Long userId);

}
