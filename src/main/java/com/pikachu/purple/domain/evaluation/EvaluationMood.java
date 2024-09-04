package com.pikachu.purple.domain.evaluation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationMood {

    private String moodName;

    @Builder
    public EvaluationMood(String moodName) {
        this.moodName = moodName;
    }

}
