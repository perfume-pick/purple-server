package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationDomainService {

    void create(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    );

    ReviewEvaluation findByReviewId(Long reviewId);
}
