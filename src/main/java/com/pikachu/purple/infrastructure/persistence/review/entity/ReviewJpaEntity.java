package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.common.ReviewType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewJpaEntity extends BaseEntity {

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_type")
    private ReviewType reviewType;

    @Builder
    public ReviewJpaEntity(
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

    public static ReviewJpaEntity toJpaEntity(Review review) {
        return ReviewJpaEntity.builder()
            .reviewId(review.getReviewId())
            .perfumeId(review.getPerfumeId())
            .userId(review.getUserId())
            .content(review.getContent())
            .reviewType(review.getReviewType())
            .build();
    }

    public static Review toDomain(ReviewJpaEntity reviewJpaEntity) {
        return Review.builder()
            .reviewId(reviewJpaEntity.getReviewId())
            .perfumeId(reviewJpaEntity.getPerfumeId())
            .userId(reviewJpaEntity.getUserId())
            .content(reviewJpaEntity.getContent())
            .reviewType(reviewJpaEntity.getReviewType())
            .build();
    }

}
