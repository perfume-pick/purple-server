package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationRepository {

    void create(ReviewEvaluation reviewEvaluation);

    ReviewEvaluation find();

    ReviewEvaluation findByReviewId(Long reviewId);

    ReviewEvaluation findByPerfumeId(Long perfumeId);

    void delete(Long reviewId);

    void update(ReviewEvaluation reviewEvaluation);

}
