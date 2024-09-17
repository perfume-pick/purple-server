package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.mood.repository.MoodJpaRepository;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewMoodJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewJpaAdaptor implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final MoodJpaRepository moodJpaRepository;
    private final ReviewMoodJpaRepository reviewMoodJpaRepository;

    @Override
    public Review create(Long userId, Long perfumeId, Review review) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
            .orElseThrow(() -> new BusinessException(ErrorCode.PERFUME_NOT_FOUND));

        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.builder()
            .userJpaEntity(userJpaEntity)
            .perfumeJpaEntity(perfumeJpaEntity)
            .content(review.getContent())
            .reviewType(review.getType())
            .build();

        ReviewJpaEntity reviewJpaEntitySaved = reviewJpaRepository.save(reviewJpaEntity);
        return ReviewJpaEntity.toDomain(reviewJpaEntitySaved);
    }

    @Override
    public List<Review> findAllByUserId(Long userId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findByUserId(userId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Review getByIdAndUserId(
        Long reviewId,
        Long userId
    ) {
        ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findByReviewIdAndUserId(
            reviewId,
            userId
        ).orElseThrow(() -> ReviewNotFoundException);

        return ReviewJpaEntity.toDomain(reviewJpaEntity);
    }

    @Override
    public Review findWithPerfumeById(Long reviewId) {
        ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findById(reviewId)
            .orElseThrow(() -> ReviewNotFoundException);

        return ReviewJpaEntity.toDomain(reviewJpaEntity);
    }

    @Override
    public void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    ) {
        ReviewJpaEntity reviewJpaEntity = reviewJpaRepository.findById(reviewId)
            .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND));

        List<MoodJpaEntity> moodJpaEntities = moodJpaRepository.findAllByNameIn(moodNames);
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = moodJpaEntities.stream()
            .map(moodJpaEntity -> ReviewMoodJpaEntity.builder()
                .reviewJpaEntity(reviewJpaEntity)
                .moodJpaEntity(moodJpaEntity)
                .build())
            .toList();

        reviewMoodJpaRepository.saveAll(reviewMoodJpaEntities);
    }

    @Override
    public void save(Review review) {
        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.toJpaEntity(review);
        reviewJpaRepository.save(reviewJpaEntity);
    }

    @Override
    public void deleteById(Review review) {
        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.toJpaEntity(review);
        reviewJpaRepository.delete(reviewJpaEntity);
    }



}
