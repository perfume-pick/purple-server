package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.enums.Mood;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewMoodId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "review_mood")
@IdClass(ReviewMoodId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewMoodJpaEntity extends BaseEntity {

    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(
        name = "mood_name",
        columnDefinition = "varchar(255)",
        nullable = false
    )
    private Mood mood;

}
