package com.pikachu.purple.infrastructure.persistence.rating.entity;

import com.pikachu.purple.domain.rating.Rating;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.common.EntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "rating")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RatingJpaEntity extends BaseEntity {

    @Id
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @Column(nullable = false)
    private Double score;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_status")
    private EntityStatus entityStatus;

    @Builder
    public RatingJpaEntity(
        Long ratingId,
        Long userId,
        Long reviewId,
        Double score,
        EntityStatus entityStatus
    ) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.score = score;
        this.entityStatus = entityStatus;
    }


    public static Rating toDomain(RatingJpaEntity ratingJpaEntity) {
        return Rating.builder()
            .ratingId(ratingJpaEntity.getRatingId())
            .userId(ratingJpaEntity.getUserId())
            .reviewId(ratingJpaEntity.getReviewId())
            .score(ratingJpaEntity.getScore())
            .build();
    }

    public static RatingJpaEntity toJpaEntity(Rating rating) {
        return RatingJpaEntity.builder()
            .ratingId(rating.getRatingId())
            .userId(rating.getUserId())
            .reviewId(rating.getReviewId())
            .score(rating.getScore())
            .entityStatus(rating.getEntityStatus())
            .build();
    }

}
