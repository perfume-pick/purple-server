package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.perfume.enums.NoteType;
import lombok.Getter;

@Getter
public class Note {

    private final String name;
    private final NoteType type;

    public Note(
        String name,
        NoteType type
    ) {
        this.name = name;
        this.type = type;
    }

}
