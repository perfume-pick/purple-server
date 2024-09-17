package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private UserJpaEntity userJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "perfume_id",
        nullable = false
    )
    private PerfumeJpaEntity perfumeJpaEntity;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "user_id"),
        @JoinColumn(name = "perfume_id")
    })
    private StarRatingJpaEntity starRatingJpaEntity;

    @Column(name = "like_count")
    private int likeCount;

    public static Review toDomain(ReviewJpaEntity jpaEntity) {
        return Review.builder()
            .id(jpaEntity.getId())
            .user(UserJpaEntity.toDomain(jpaEntity.getUserJpaEntity()))
            .perfume(PerfumeJpaEntity.toDomain(jpaEntity.getPerfumeJpaEntity()))
            .content(jpaEntity.getContent())
            .type(jpaEntity.getReviewType())
            .starRating(StarRatingJpaEntity.toDomain(jpaEntity.getStarRatingJpaEntity()))
            .build();
    }

}
