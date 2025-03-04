package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import com.pikachu.purple.application.review.port.out.MoodRepository;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.infrastructure.persistence.review.entity.MoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.MoodJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewMoodJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoodJpaAdaptor implements MoodRepository {

    private final MoodJpaRepository moodJpaRepository;
    private final ReviewMoodJpaRepository reviewMoodJpaRepository;

    @Override
    public List<Mood> findAll() {
        List<MoodJpaEntity> evaluationMoodJpaEntities = moodJpaRepository.findAll();

        return evaluationMoodJpaEntities.stream()
            .map(MoodJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Mood> findAllByReviewId(Long reviewId) {
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = reviewMoodJpaRepository
            .findAllByReviewId(reviewId);

        return reviewMoodJpaEntities.stream()
            .map(
                reviewMoodJpaEntity ->
                    MoodJpaEntity.toDomain(
                        reviewMoodJpaEntity.getMoodJpaEntity()
                    ))
            .toList();
    }

}
