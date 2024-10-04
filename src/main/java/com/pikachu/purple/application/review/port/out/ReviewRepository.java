package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;

public interface ReviewRepository {

    Review create(Long userId, Long perfumeId, Review review);

    void deleteById(Long id);

    void createReviewMoods(Long reviewId, List<String> moodNames);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByCreatedAtDesc(Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreDesc(Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreAsc(Long perfumeId);

    Review find(Long reviewId);

    List<Review> findAllWithEvaluation(ReviewType reviewType, String updatedDate);

    void deleteReviewMoods(Long reviewId);

    void updateReviewMood(Long reviewId, List<String> moodNames);

    Review findWithPerfume(Long reviewId);

    void update(Long reviewId, String content, ReviewType reviewType);

}
