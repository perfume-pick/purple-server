package com.pikachu.purple.domain.mainAccord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainAccord {

    @JsonIgnore
    private Long mainAccordId;
    @JsonIgnore
    private Long perfumeId;
    private String noteName;
    private int accordValue;

    @Builder
    public MainAccord(
            Long mainAccordId,
            Long perfumeId,
            String noteName,
            Integer accordValue
    ) {
        this.mainAccordId = mainAccordId;
        this.perfumeId = perfumeId;
        this.noteName = noteName;
        this.accordValue = accordValue;
    }

}
