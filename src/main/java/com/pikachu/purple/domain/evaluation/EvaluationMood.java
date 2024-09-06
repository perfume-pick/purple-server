package com.pikachu.purple.domain.evaluation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationMood {

    private Long evaluationMoodId;
    private Long userId;
    private Long perfumeId;
    private String moodName;

    @Builder
    public EvaluationMood(
        Long evaluationMoodId,
        Long userId,
        Long perfumeId,
        String moodName
    ) {
        this.evaluationMoodId = evaluationMoodId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.moodName = moodName;
    }

}
