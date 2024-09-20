package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface ReviewRepository {

    Review create(
        Long userId,
        Long perfumeId,
        Review review
    );

    Review updateContent(
        Long reviewId,
        String content
    );

    void deleteById(Long id);

    void createReviewMoods(
        Long reviewId,
        List<String> moodNames
    );

    List<Review> findAllWithReviewEvaluationAndMoodOrderByCreatedAtDesc(Long perfumeId);

    List<Review> findAllWithStarRatingAndReviewEvaluationAndMoodOrderByScoreDesc(Long perfumeId);

    List<Review> findAllWithStarRatingAndReviewEvaluationAndMoodOrderByScoreAsc(Long perfumeId);

    Review findById(Long reviewId);

}
