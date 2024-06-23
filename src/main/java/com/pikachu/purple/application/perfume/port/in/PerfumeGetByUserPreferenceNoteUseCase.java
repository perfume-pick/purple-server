package com.pikachu.purple.application.perfume.port.in;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;

public interface PerfumeGetByUserPreferenceNoteUseCase {

    Result invoke();

    record Result(
        List<UserPreferenceNote> userPreferenceNotes,
        List<Perfume> perfumes
    ) {

    }

}
