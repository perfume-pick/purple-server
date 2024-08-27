package com.pikachu.purple.domain.review;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    private Long reviewId;
    private Long perfumeId;
    private Long userId;
    private Long ratingId;
    private String content;

    @Builder
    public Review(
        Long reviewId,
        Long perfumeId,
        Long userId,
        Long ratingId,
        String content
    ) {
        this.reviewId = reviewId;
        this.perfumeId = perfumeId;
        this.userId = userId;
        this.ratingId = ratingId;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
