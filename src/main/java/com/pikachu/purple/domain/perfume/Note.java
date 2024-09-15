package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.perfume.enums.NoteType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {

    private String name;
    private NoteType type;

}
