package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewRepository {

    Review create(Long userId, Long perfumeId, Review review);

    void createReviewMoods(Long reviewId, List<String> moodNames);

    Review find(Long reviewId);

    Review findWithPerfume(Long reviewId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByCreatedAtDesc(Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreDesc(Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreAsc(Long perfumeId);

    List<Review> findAllWithEvaluation(ReviewType reviewType, String updatedDate);

    void update(Long reviewId, String content, ReviewType reviewType);

    void updateReviewMood(Long reviewId, List<String> moodNames);

    void deleteReviewMoods(Long reviewId);

    void deleteById(Long id);

}
