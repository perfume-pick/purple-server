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
    private Double score;

    @Builder
    public Rating(
        Long ratingId,
        Long userId,
        Long perfumeId,
        Double score
    ){
        this.ratingId = ratingId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.score = score;
    }

}
