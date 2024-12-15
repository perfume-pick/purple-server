package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.perfume.enums.NoteType;
import lombok.Getter;

@Getter
public class Note {

    private final String name;
    private final String koreanName;
    private final NoteType type;

    public Note(
        String name,
        String koreanName,
        NoteType type
    ) {
        this.name = name;
        this.koreanName = koreanName;
        this.type = type;
    }

}
