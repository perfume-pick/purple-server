package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewMoodId;
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
@Table(name = "review_mood")
@IdClass(ReviewMoodId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewMoodJpaEntity extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewJpaEntity reviewJpaEntity;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mood_name")
    private MoodJpaEntity moodJpaEntity;

}
