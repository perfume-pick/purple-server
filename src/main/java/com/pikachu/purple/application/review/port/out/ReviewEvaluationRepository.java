package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationRepository {

    void create(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    );

    ReviewEvaluation findByReviewId(Long reviewId);
}
