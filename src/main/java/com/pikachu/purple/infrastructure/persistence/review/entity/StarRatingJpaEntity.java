package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.StarRatingId;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "star_rating")
@IdClass(StarRatingId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StarRatingJpaEntity extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private PerfumeJpaEntity perfumeJpaEntity;

    @Column(name = "score")
    private int score;

    public void updateScore(int score) {
        this.score = score;
    }

    public static StarRating toDomain(StarRatingJpaEntity jpaEntity) {
        return StarRating.builder()
            .user(UserJpaEntity.toDomain(jpaEntity.getUserJpaEntity()))
            .perfume(PerfumeJpaEntity.toDomain(jpaEntity.getPerfumeJpaEntity()))
            .score(jpaEntity.getScore())
            .build();
    }

}
