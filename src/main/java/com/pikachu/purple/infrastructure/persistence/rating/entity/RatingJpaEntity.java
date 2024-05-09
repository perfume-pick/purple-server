package com.pikachu.purple.infrastructure.persistence.rating.entity;

import com.pikachu.purple.domain.rating.Rating;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class RatingJpaEntity {

    @Id
    @Column(name = "Rating_id")
    private Long ratingId;

    @Column(name = "User_id", nullable = false)
    private Long userId;

    @Column(name = "Perfume_id", nullable = false)
    private Long perfumeId;

    @Column(nullable = false)
    private Double score;

    @Builder
    public RatingJpaEntity(
        Long ratingId,
        Long userId,
        Long perfumeId,
        Double score
    ) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.score = score;
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
            .build();
    }

}
