package com.pikachu.purple.domain.review;

import com.pikachu.purple.infrastructure.persistence.common.ReviewType;
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
    private String content;
    private ReviewType reviewType;

    @Builder
    public Review(
        Long reviewId,
        Long perfumeId,
        Long userId,
        String content,
        ReviewType reviewType
    ) {
        this.reviewId = reviewId;
        this.perfumeId = perfumeId;
        this.userId = userId;
        this.content = content;
        this.reviewType = reviewType;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
