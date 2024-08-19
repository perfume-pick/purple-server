package com.pikachu.purple.domain.rating;

import com.pikachu.purple.infrastructure.persistence.common.EntityStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {

    private Long ratingId;
    private Long userId;
    private Long reviewId;
    private Double score;
    private EntityStatus entityStatus;

    @Builder
    public Rating(
        Long ratingId,
        Long userId,
        Long reviewId,
        Double score
    ){
        this.ratingId = ratingId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.score = score;
        this.entityStatus = EntityStatus.ACTIVE;
    }

    public void updateScore(Double score) {
        this.score = score;
    }

}
