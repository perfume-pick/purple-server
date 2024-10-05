package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationRepository {

    void create(
        ReviewEvaluation reviewEvaluation
    );

    ReviewEvaluation find(Long reviewId);

    void deleteAll(Long reviewId);

    void update(
        ReviewEvaluation reviewEvaluation
    );

}
