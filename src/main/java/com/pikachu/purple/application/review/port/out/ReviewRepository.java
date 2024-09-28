package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
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

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByCreatedAtDesc(Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreDesc(Long perfumeId);

    List<Review> findAllWithPerfumeAndReviewEvaluationAndMoodOrderByScoreAsc(Long perfumeId);

    Review findById(Long reviewId);

    List<Review> findAllWithEvaluation(
        ReviewType reviewType,
        String updatedDate
    );
    
}
