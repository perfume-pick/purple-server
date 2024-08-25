package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE review SET active = false WHERE review_id = ?")
@Where(clause = "active = true")
public class ReviewJpaEntity extends BaseEntity {

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "rating_id", nullable = false)
    private Long ratingId;

    @Column(name = "content", nullable = false)
    private String content;

    private boolean active;

    @Builder
    public ReviewJpaEntity(
        Long reviewId,
        Long perfumeId,
        Long userId,
        Long ratingId,
        String content,
        boolean active
    ) {
        this.reviewId = reviewId;
        this.perfumeId = perfumeId;
        this.userId = userId;
        this.ratingId = ratingId;
        this.content = content;
        this.active = active;
    }

    public static ReviewJpaEntity toJpaEntity(Review review) {
        return ReviewJpaEntity.builder()
            .reviewId(review.getReviewId())
            .perfumeId(review.getPerfumeId())
            .userId(review.getUserId())
            .ratingId(review.getRatingId())
            .content(review.getContent())
            .active(review.isActive())
            .build();
    }

    public static Review toDomain(ReviewJpaEntity reviewJpaEntity) {
        return Review.builder()
            .reviewId(reviewJpaEntity.getReviewId())
            .perfumeId(reviewJpaEntity.getPerfumeId())
            .userId(reviewJpaEntity.getUserId())
            .ratingId(reviewJpaEntity.getRatingId())
            .content(reviewJpaEntity.getContent())
            .build();
    }

}
