package com.pikachu.purple.domain.mood;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mood {

    private String moodName;

    @Builder
    public Mood(String moodName) {
        this.moodName = moodName;
    }

}
