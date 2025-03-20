package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.PerfumeNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.Mood;
import com.pikachu.purple.domain.review.enums.ReviewType;
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
class ReviewJpaAdaptor implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final PerfumeJpaRepository perfumeJpaRepository;
    private final UserJpaRepository userJpaRepository;
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
            .userId(userJpaEntity.getId())
            .perfumeId(perfumeJpaEntity.getId())
            .starRatingId(starRatingJpaEntity.getId())
            .content(review.getContent())
            .reviewType(review.getType())
            .build();

        reviewJpaRepository.save(reviewJpaEntity);

        ReviewJpaEntity reviewJpaEntitySaved = findEntityById(review.getId());

        return ReviewJpaEntity.toDomain(reviewJpaEntitySaved);
    }

    @Override
    public void createAll(List<Review> reviews) {
        List<ReviewJpaEntity> reviewJpaEntities = reviews.stream()
            .map(ReviewJpaEntity::toJpaEntity)
            .toList();

        reviewJpaRepository.saveAll(reviewJpaEntities);
    }

    @Override
    public void createReviewMoods(
        Long reviewId,
        List<Mood> moods
    ) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);

        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = moods.stream()
            .map(mood -> ReviewMoodJpaEntity.builder()
                .reviewId(reviewJpaEntity.getId())
                .mood(mood)
                .build())
            .toList();

        reviewMoodJpaRepository.saveAll(reviewMoodJpaEntities);
    }

    @Override
    public Review findByReviewId(Long reviewId) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);
        return ReviewJpaEntity.toDomain(reviewJpaEntity);
    }

    @Override
    public Review findUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        return reviewJpaRepository.findByUserIdAndPerfumeId(
                userId,
                perfumeId
            )
            .map(ReviewJpaEntity::toDomain)
            .orElse(null);
    }

    @Override
    public List<Review> findAllByUserId(Long userId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findAllByUserId(userId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAll(ReviewType reviewType) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository.findAllByReviewType(reviewType);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByUserIdOrderByLikeCountDesc(Long userId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByUserIdOrderByLikeCountDesc(userId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByPerfumeIdOrderByLikeCountDesc(Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByPerfumeIdOrderByLikeCountDesc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByUserIdOrderByCreatedAtDesc(Long userId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByUserIdOrderByCreatedAtDesc(userId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByPerfumeIdOrderByCreatedAtDesc(Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByPerfumeIdOrderByCreatedAtDesc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByUserIdOrderByScoreDesc(Long userId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByUserIdOrderByScoreDesc(userId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByPerfumeIdOrderByScoreDesc(Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByPerfumeIdOrderByScoreDesc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByUserIdOrderByScoreAsc(Long userId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByUserIdOrderByScoreAsc(userId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Review> findAllByPerfumeIdOrderByScoreAsc(Long perfumeId) {
        List<ReviewJpaEntity> reviewJpaEntities = reviewJpaRepository
            .findAllByPerfumeIdOrderByScoreAsc(perfumeId);

        return reviewJpaEntities.stream()
            .map(ReviewJpaEntity::toDomain)
            .toList();
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
    public void updateReviewMood(
        Long reviewId,
        List<Mood> moods
    ) {
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = reviewMoodJpaRepository.findByReviewId(reviewId);
        reviewMoodJpaRepository.deleteAll(reviewMoodJpaEntities);

        createReviewMoods(
            reviewId,
            moods
        );
    }

    @Override
    public void delete(Long reviewId) {
        reviewJpaRepository.deleteById(reviewId);
    }

    @Override
    public void deleteReviewMoods(Long reviewId) {
        List<ReviewMoodJpaEntity> reviewMoodJpaEntities = reviewMoodJpaRepository.findByReviewId(reviewId);
        reviewMoodJpaRepository.deleteAll(reviewMoodJpaEntities);
    }

    @Override
    public void increaseLikeCount(Long reviewId) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);
        reviewJpaEntity.increase();
        reviewJpaRepository.save(reviewJpaEntity);
    }

    @Override
    public void decreaseLikeCount(Long reviewId) {
        ReviewJpaEntity reviewJpaEntity = findEntityById(reviewId);
        reviewJpaEntity.decrease();
        reviewJpaRepository.save(reviewJpaEntity);
    }

    @Override
    public int count() {
        return (int) reviewJpaRepository.count();
    }

    @Override
    public int count(Long userId) {
        return reviewJpaRepository.countByUserId(userId);
    }

}
