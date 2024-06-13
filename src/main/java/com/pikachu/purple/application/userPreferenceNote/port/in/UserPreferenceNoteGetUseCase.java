package com.pikachu.purple.application.userPreferenceNote.port.in;

import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;

public interface UserPreferenceNoteGetUseCase {

    Result invoke();

    record Result(List<UserPreferenceNote> userPreferenceNoteList){

    }

}
