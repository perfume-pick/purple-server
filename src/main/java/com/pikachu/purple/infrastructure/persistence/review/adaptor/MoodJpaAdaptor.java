package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.review.port.out.MoodRepository;
import com.pikachu.purple.domain.review.enums.Mood;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewMoodJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MoodJpaAdaptor implements MoodRepository {

    private final ReviewMoodJpaRepository reviewMoodJpaRepository;

    @Override
    public List<Mood> findAllByReviewId(Long reviewId) {
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = reviewMoodJpaRepository
            .findAllByReviewId(reviewId);

        return reviewMoodJpaEntities.stream()
            .map(ReviewMoodJpaEntity::getMood)
            .toList();
    }

}
