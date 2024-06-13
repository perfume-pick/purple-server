package com.pikachu.purple.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPreferenceNote {

    private Long id;
    private Long userId;
    private String noteName;

    @Builder
    public UserPreferenceNote(
        Long id,
        Long userId,
        String noteName
    ) {
        this.id = id;
        this.userId = userId;
        this.noteName = noteName;
    }

}
