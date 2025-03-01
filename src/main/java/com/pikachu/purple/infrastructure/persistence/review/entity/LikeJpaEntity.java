package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Like;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.LikeId;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "likes")
@IdClass(LikeId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeJpaEntity extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    public static Like toDomain(LikeJpaEntity jpaEntity) {
        Like domain = new Like();
        domain.setUser(UserJpaEntity.toDummy(jpaEntity.getUserId()));
        domain.setReview(ReviewJpaEntity.toDummy(jpaEntity.getReviewId()));

        return domain;
    }

}
