package com.pikachu.purple.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPreferenceNote {

    private Long userPreferenceNoteId;
    private Long userId;
    private String noteName;

    @Builder
    public UserPreferenceNote(
        Long userPreferenceNoteId,
        Long userId,
        String noteName
    ) {
        this.userPreferenceNoteId = userPreferenceNoteId;
        this.userId = userId;
        this.noteName = noteName;
    }

}
