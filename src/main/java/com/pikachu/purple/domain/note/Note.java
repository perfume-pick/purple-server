package com.pikachu.purple.domain.note;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {

    private String noteName;

    @Builder
    public Note(String noteName){
        this.noteName = noteName;
    }

}
