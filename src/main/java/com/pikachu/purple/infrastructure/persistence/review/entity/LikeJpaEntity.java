package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.LikeId;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewMoodId;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "likes")
@IdClass(LikeId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeJpaEntity extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewJpaEntity reviewJpaEntity;

}
