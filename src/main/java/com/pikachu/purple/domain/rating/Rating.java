package com.pikachu.purple.domain.rating;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {

    private Long ratingId;
    private Long userId;
    private Long perfumeId;
    private int score;
    private boolean active;

    @Builder
    public Rating(
        Long ratingId,
        Long userId,
        Long perfumeId,
        int score
    ){
        this.ratingId = ratingId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.score = score;
        this.active = true;
    }

    public void updateScore(int score) {
        this.score = score;
    }

}
