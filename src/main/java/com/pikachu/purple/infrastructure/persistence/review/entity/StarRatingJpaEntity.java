package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "star_rating")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRatingJpaEntity extends BaseEntity {

    @Id
    @Column(name = "star_rating_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Column(name = "score")
    private int score;

    public void updateScore(int score) {
        this.score = score;
    }

    public static StarRating.StarRatingBuilder buildDefault(StarRatingJpaEntity jpaEntity) {
        return StarRating.builder()
            .id(jpaEntity.getId())
            .score(jpaEntity.getScore());
    }

    public static StarRating toDomain(StarRatingJpaEntity jpaEntity) {
        return buildDefault(jpaEntity)
            .build();
    }

    public static StarRating toDomainWithUserAndPerfume(StarRatingJpaEntity jpaEntity) {
        return buildDefault(jpaEntity)
            .user(UserJpaEntity.toDomain(jpaEntity.getUserJpaEntity()))
            .perfume(PerfumeJpaEntity.toDomain(jpaEntity.getPerfumeJpaEntity()))
            .build();
    }

    public static StarRating toDomainWithPerfumeAccord(StarRatingJpaEntity jpaEntity) {
        return StarRating.builder()
            .user(UserJpaEntity.toDomain(jpaEntity.getUserJpaEntity()))
            .perfume(PerfumeJpaEntity.toDomainWithPerfumeAccord(jpaEntity.getPerfumeJpaEntity()))
            .score(jpaEntity.getScore())
            .build();
    }

}
