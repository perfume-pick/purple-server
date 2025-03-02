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

    @OneToOne(
        mappedBy = "starRatingJpaEntity",
        fetch = FetchType.LAZY
    )
    private ReviewJpaEntity reviewJpaEntity;


    public void updateScore(int score) {
        this.score = score;
    }

    public static StarRating toDomain(StarRatingJpaEntity jpaEntity) {
        StarRating domain = new StarRating(
            jpaEntity.getId(),
            jpaEntity.getScore(),
            jpaEntity.getUpdatedAt()
        );
        domain.setUser(UserJpaEntity.toDummy(jpaEntity.getUserJpaEntity()));
        domain.setPerfume(PerfumeJpaEntity.toDummy(jpaEntity.getPerfumeJpaEntity()));

        return domain;
    }

    public static StarRating toDomainWithPerfume(StarRatingJpaEntity jpaEntity) {
        StarRating domain = toDomain(jpaEntity);
        domain.setPerfume(PerfumeJpaEntity.toDomain(jpaEntity.getPerfumeJpaEntity()));

        return domain;
    }

    public static StarRating toFullDomain(StarRatingJpaEntity jpaEntity) {
        StarRating domain = toDomain(jpaEntity);
        domain.setReview(ReviewJpaEntity.toFullDomain(jpaEntity.getReviewJpaEntity()));
        return domain;
    }

}
