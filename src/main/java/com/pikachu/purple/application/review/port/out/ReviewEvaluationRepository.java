package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationRepository {

    void createAll(ReviewEvaluation reviewEvaluation);

    ReviewEvaluation findAll();

    ReviewEvaluation findAll(Long reviewId);

    void deleteAll(Long reviewId);

    void updateAll(ReviewEvaluation reviewEvaluation);

}
