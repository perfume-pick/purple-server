package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(
        name = "star_rating_id"
    )
    private StarRatingJpaEntity starRatingJpaEntity;

    @Column(name = "like_count")
    private int likeCount;

    @OneToMany(mappedBy = "reviewJpaEntity")
    @OrderBy("fieldCode asc, optionCode asc")
    private List<ReviewEvaluationJpaEntity> reviewEvaluationJpaEntities = new ArrayList<>();

    @OneToMany(mappedBy = "reviewJpaEntity")
    private List<ReviewMoodJpaEntity> reviewMoodJpaEntities = new ArrayList<>();

    private static Review.ReviewBuilder buildDefault(ReviewJpaEntity jpaEntity) {
        return Review.builder()
            .id(jpaEntity.getId())
            .user(UserJpaEntity.toDomain(jpaEntity.getUserJpaEntity()))
            .perfume(PerfumeJpaEntity.toDummy(jpaEntity.getPerfumeJpaEntity()))
            .starRating(StarRatingJpaEntity.toDomain(jpaEntity.getStarRatingJpaEntity()))
            .content(jpaEntity.getContent())
            .date(jpaEntity.getUpdatedAt())
            .likeCount(jpaEntity.getLikeCount())
            .type(jpaEntity.getReviewType());
    }

    public static Review toFullDomain(ReviewJpaEntity jpaEntity) {
        return buildDefault(jpaEntity)
            .perfume(PerfumeJpaEntity.toDomain(jpaEntity.getPerfumeJpaEntity()))
            .evaluation(ReviewEvaluationJpaEntity.toDomain(jpaEntity.getReviewEvaluationJpaEntities()))
            .moods(jpaEntity.getReviewMoodJpaEntities().stream()
                .map(reviewMoodJpaEntity ->
                    MoodJpaEntity.toDomain(reviewMoodJpaEntity.getMoodJpaEntity())
                )
                .toList())
            .build();
    }

    public static Review toDomain(ReviewJpaEntity jpaEntity) {
        return buildDefault(jpaEntity).build();
    }

    public static Review toDomainWithPerfume(ReviewJpaEntity jpaEntity){
        return buildDefault(jpaEntity)
            .perfume(PerfumeJpaEntity.toDomain(jpaEntity.getPerfumeJpaEntity()))
            .build();
    }

    public static Review toDomainWithEvaluation(ReviewJpaEntity jpaEntity) {
        return buildDefault(jpaEntity)
            .evaluation(ReviewEvaluationJpaEntity.toDomain(jpaEntity.reviewEvaluationJpaEntities))
            .build();
    }

    public void update(
        String content,
        ReviewType reviewType
    ) {
        this.content = content;
        this.reviewType = reviewType;
    }
}
