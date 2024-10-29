package com.pikachu.purple.application.review.service.domain.impl;

import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDomainServiceImpl implements ReviewDomainService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review create(
        Long userId,
        Long perfumeId,
        String content,
        ReviewType reviewType
    ) {
        Long reviewId = IdUtil.generateId();

        Review review = Review.builder()
            .id(reviewId)
            .content(content)
            .type(reviewType)
            .build();

        return reviewRepository.create(userId, perfumeId, review);
    }

    public void delete(Long id) {
        reviewRepository.delete(id);
    }

    @Override
    public void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    ) {
        reviewRepository.createReviewMoods(
            reviewId,
            moodNames
        );
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByCreatedAtDesc(
        Long userId,
        Long perfumeId
    ) {
        return reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByCreatedAtDesc(
            userId,
            perfumeId
        );
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreDesc(
        Long userId,
        Long perfumeId
    ) {
        return reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreDesc(
            userId,
            perfumeId
        );
    }

    @Override
    public List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreAsc(
        Long userId,
        Long perfumeId
    ) {
        return reviewRepository.findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreAsc(
            userId,
            perfumeId
        );
    }

    @Override
    public Review findWithPerfumeAndReviewEvaluationAndMood(
        Long userId,
        Long perfumeId
    ) {
        return reviewRepository.findWithPerfumeAndReviewEvaluationAndMood(
            userId,
            perfumeId
        );
    }

    @Override
    public Review find(Long reviewId) {
        return reviewRepository.find(reviewId);
    }

    @Override
    public List<Review> findAllWithEvaluation(
        ReviewType reviewType,
        String updatedDate
    ) {
        return reviewRepository.findAllWithEvaluation(
            reviewType,
            updatedDate
        );
    }

    @Override
    public void deleteReviewMoods(Long reviewId) {
        reviewRepository.deleteReviewMoods(reviewId);
    }

    @Override
    public void updateReviewMood(
        Long reviewId,
        List<String> moodNames
    ) {
        reviewRepository.updateReviewMood(
            reviewId,
            moodNames
        );
    }

    @Override
    public Review findWithPerfume(Long reviewId) {
        return reviewRepository.findWithPerfume(reviewId);
    }

    @Override
    public void update(
        Long reviewId,
        String content,
        ReviewType reviewType
    ) {
        reviewRepository.update(
            reviewId,
            content,
            reviewType
        );
    }

    @DistributedLock(
        name = "Review",
        key = "T(String).valueOf(#reviewId).concat('-likeCount')"
    )
    @Override
    public void increaseLikeCount(Long reviewId) {
        reviewRepository.increaseLikeCount(reviewId);
    }

    @DistributedLock(
        name = "Review",
        key = "T(String).valueOf(#reviewId).concat('-likeCount')"
    )
    @Override
    public void decreaseLikeCount(Long reviewId) {
        reviewRepository.decreaseLikeCount(reviewId);
    }

    @Override
    public int count() {
        return reviewRepository.count();
    }

    @Override
    public int count(Long userId) {
        return reviewRepository.count(userId);
    }

    @Override
    public List<Review> findAllWithPerfume(Long userId) {
        return reviewRepository.findAllWithPerfume(userId);
    }

}
