package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationDomainService {

    void create(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    );

    ReviewEvaluation find(Long reviewId);

    void deleteAll(Long reviewId);

    void update(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    );

}
