package com.pikachu.purple.infrastructure.persistence.review.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.repository.ReviewJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewJpaAdaptor implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public void createOnboarding(List<Review> reviews) {
        List<ReviewJpaEntity> reviewJpaEntities = reviews.stream()
            .map(ReviewJpaEntity::toJpaEntity)
            .toList();

        reviewJpaRepository.saveAll(reviewJpaEntities);
    }

    @Override
    public Long create(Review review) {
        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.toJpaEntity(review);
        return reviewJpaRepository.save(reviewJpaEntity).getReviewId();
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
    public void save(Review review) {
        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.toJpaEntity(review);
        reviewJpaRepository.save(reviewJpaEntity);
    }

    @Override
    public void delete(Review review) {
        ReviewJpaEntity reviewJpaEntity = ReviewJpaEntity.toJpaEntity(review);
        reviewJpaRepository.delete(reviewJpaEntity);
    }

}
