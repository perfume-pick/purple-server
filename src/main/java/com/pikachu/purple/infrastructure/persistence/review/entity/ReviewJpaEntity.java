package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewJpaEntity extends BaseEntity {

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "rating_id", nullable = false)
    private Long ratingId;

    @Column(name = "comment", nullable = false)
    private String comment;

}
