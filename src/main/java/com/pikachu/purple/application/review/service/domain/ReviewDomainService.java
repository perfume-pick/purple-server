package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewDomainService {

    Review create(
        Long userId,
        Long perfumeId,
        String content,
        ReviewType reviewType
    );

    void delete(Long id);

    void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    );

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByCreatedAtDesc(
        Long userId,
        Long perfumeId
    );

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreDesc(
        Long userId,
        Long perfumeId
    );

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodAndIsComplainedOrderByScoreAsc(
        Long userId,
        Long perfumeId
    );

    Review findWithPerfumeAndReviewEvaluationAndMood(
        Long userId,
        Long perfumeId
    );

    Review find(Long reviewId);

    List<Review> findAllWithEvaluation(
        ReviewType reviewType,
        String updatedDate
    );

    void deleteReviewMoods(Long reviewId);

    void updateReviewMood(
        Long reviewId,
        List<String> moodNames
    );

    Review findWithPerfume(Long reviewId);

    void update(
        Long reviewId,
        String content,
        ReviewType reviewType
    );

    void increaseLikeCount(Long reviewId);

    void decreaseLikeCount(Long reviewId);

}
