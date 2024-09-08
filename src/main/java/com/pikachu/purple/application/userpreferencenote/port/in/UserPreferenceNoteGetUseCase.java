package com.pikachu.purple.application.userpreferencenote.port.in;

import com.pikachu.purple.domain.user.UserPreferenceNote;
import java.util.List;

public interface UserPreferenceNoteGetUseCase {

    Result invoke();

    record Result(List<UserPreferenceNote> userPreferenceNotes){

    }

}
