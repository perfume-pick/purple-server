package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.mood.repository.MoodJpaRepository;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.PerfumeJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewMoodJpaRepository;
import com.pikachu.purple.infrastructure.persistence.review.repository.StarRatingJpaRepository;
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
    private final StarRatingJpaRepository starRatingJpaRepository;

    private ReviewJpaEntity findEntityById(Long reviewId) {
        return reviewJpaRepository.findById(reviewId)
            .orElseThrow(() -> ReviewNotFoundException);
    }

    @Override
    public Review create(Long userId, Long perfumeId, Review review) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        PerfumeJpaEntity perfumeJpaEntity = perfumeJpaRepository.findById(perfumeId)
            .orElseThrow(() -> PerfumeNotFoundException);
        
        StarRatingJpaEntity starRatingJpaEntity = starRatingJpaRepository.findByUserIdAndPerfumeId(
            userId, perfumeId
        ).orElseThrow();

        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.builder()
            .id(review.getId())
            .userJpaEntity(userJpaEntity)
            .perfumeJpaEntity(perfumeJpaEntity)
            .starRatingJpaEntity(starRatingJpaEntity)
            .content(review.getContent())
            .reviewType(review.getType())
            .build();

        reviewJpaRepository.save(reviewJpaEntity);

        ReviewJpaEntity reviewJpaEntitySaved = findEntityById(review.getId());

        return ReviewJpaEntity.toDomain(reviewJpaEntitySaved);
    }

    @Override
    public void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    ) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);

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
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByCreatedAtDesc(
        Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findAllByPerfumeIdOrderByCreateAtDesc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toFullDomain)
            .toList();
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreDesc(
        Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findAllByPerfumeIdOrderByScoreDesc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toFullDomain)
            .toList();
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreAsc(
        Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findAllByPerfumeIdOrderByScoreAsc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toFullDomain)
            .toList();
    }

    @Override
    public Review find(Long reviewId) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);
        return ReviewJpaEntity.toDomain(reviewJpaEntity);
    }

    @Override
    public List<Review> findAllWithEvaluation(
        ReviewType reviewType,
        String updatedDate
    ) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findAllByReviewTypeAndUpdateDate(
            reviewType,
            updatedDate
        );

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomainWithEvaluation)
            .toList();
    }

    @Override
    public void deleteReviewMoods(Long reviewId) {
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = reviewMoodJpaRepository.findByReviewId(reviewId);
        reviewMoodJpaRepository.deleteAll(reviewMoodJpaEntities);
    }

    @Override
    public void updateReviewMood(
        Long reviewId,
        List<String> moodNames
    ) {
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = reviewMoodJpaRepository.findByReviewId(reviewId);
        reviewMoodJpaRepository.deleteAll(reviewMoodJpaEntities);

        createReviewMoods(
            reviewId,
            moodNames
        );
    }

    @Override
    public Review findWithPerfume(Long reviewId) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);

        return ReviewJpaEntity.toDomainWithPerfume(reviewJpaEntity);
    }

    @Override
    public void update(
        Long reviewId,
        String content,
        ReviewType reviewType
    ) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);
        reviewJpaEntity.update(
            content,
            reviewType
        );

        reviewJpaRepository.save(reviewJpaEntity);
    }


    @Override
    public void deleteById(Long reviewId) {
        reviewJpaRepository.deleteById(reviewId);
    }

}
