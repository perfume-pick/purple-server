package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewRepository {

    Review create(Long userId, Long perfumeId, Review review);

    void createReviewMoods(Long reviewId, List<String> moodNames);

    Review find(Long reviewId);

    Review findWithPerfume(Long reviewId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByLikeCountDesc(Long userId, Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByCreatedAtDesc(Long userId, Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByScoreDesc(Long userId, Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodsAndIsComplainedAndIsLikedOrderByScoreAsc(Long userId, Long perfumeId);

    List<Review> findAllWithEvaluation(ReviewType reviewType);

    void update(Long reviewId, String content, ReviewType reviewType);

    void updateReviewMood(Long reviewId, List<String> moodNames);

    void deleteReviewMoods(Long reviewId);

    void delete(Long id);

    Review findWithPerfumeAndReviewEvaluationAndMood(Long userId, Long perfumeId);

    void increaseLikeCount(Long reviewId);

    void decreaseLikeCount(Long reviewId);

    int count();

    int count(Long userId);

    List<Review> findAllWithPerfume(Long userId);

}
