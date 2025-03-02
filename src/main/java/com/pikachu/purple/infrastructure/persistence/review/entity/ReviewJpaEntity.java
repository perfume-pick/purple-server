package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewJpaEntity extends BaseEntity {

    @Id
    @Column(name = "review_id")
    private Long id;

    @Column(
        name = "user_id",
        nullable = false
    )
    private Long userId;

    @Column(
        name = "perfume_id",
        nullable = false
    )
    private Long perfumeId;

    @Column(
        name = "content",
        columnDefinition = "text",
        nullable = false
    )
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "review_type",
        columnDefinition = "char(6)",
        nullable = false
    )
    private ReviewType reviewType;

    @Column(
        name = "star_rating_id"
    )
    private Long starRatingId;

    @Column(name = "like_count")
    private int likeCount;

    public void update(
        String content,
        ReviewType reviewType
    ) {
        this.content = content;
        this.reviewType = reviewType;
    }

    public static Review toDomain(ReviewJpaEntity jpaEntity) {
        Review domain = new Review(
            jpaEntity.getId(),
            jpaEntity.getContent(),
            jpaEntity.getReviewType(),
            jpaEntity.getUpdatedAt(),
            jpaEntity.getLikeCount()
        );
        domain.setStarRating(StarRatingJpaEntity.toDummy(jpaEntity.getStarRatingId()));
        domain.setUser(UserJpaEntity.toDummy(jpaEntity.getUserId()));
        domain.setPerfume(PerfumeJpaEntity.toDummy(jpaEntity.getPerfumeId()));

        return domain;
    }

    public static Review toDummy(Long reviewId) {
        return Review.builder()
            .id(reviewId)
            .build();
    }

    public void increase() {
        this.likeCount++;
    }

    public void decrease() {
        this.likeCount--;
    }

}
