package com.pikachu.purple.application.review.service.domain;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationDomainService {

    void createAll(ReviewEvaluation reviewEvaluation);

    ReviewEvaluation findAll();

    ReviewEvaluation findAll(Long reviewId);

    void deleteAll(Long reviewId);

    void updateAll(ReviewEvaluation reviewEvaluation);

}
