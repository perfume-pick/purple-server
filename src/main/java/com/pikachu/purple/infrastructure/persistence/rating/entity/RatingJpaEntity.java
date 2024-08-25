package com.pikachu.purple.infrastructure.persistence.rating.entity;

import com.pikachu.purple.domain.rating.Rating;
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
@Table(name = "rating")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE rating SET active = false WHERE rating_id = ?")
@Where(clause = "active = true")
public class RatingJpaEntity extends BaseEntity {

    @Id
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private boolean active;

    @Builder
    public RatingJpaEntity(
        Long ratingId,
        Long userId,
        Long perfumeId,
        int score,
        boolean active
    ) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.score = score;
        this.active = active;
    }


    public static Rating toDomain(RatingJpaEntity ratingJpaEntity) {
        return Rating.builder()
            .ratingId(ratingJpaEntity.getRatingId())
            .userId(ratingJpaEntity.getUserId())
            .perfumeId(ratingJpaEntity.getPerfumeId())
            .score(ratingJpaEntity.getScore())
            .build();
    }

    public static RatingJpaEntity toJpaEntity(Rating rating) {
        return RatingJpaEntity.builder()
            .ratingId(rating.getRatingId())
            .userId(rating.getUserId())
            .perfumeId(rating.getPerfumeId())
            .score(rating.getScore())
            .active(rating.isActive())
            .build();
    }

}
