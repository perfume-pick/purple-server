package com.pikachu.purple.application.review.port.in.reviewevaluation;


import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface GetReviewEvaluationUseCase {

    Result invoke();

    Result invoke(Long reviewId);

    record Result(ReviewEvaluation reviewEvaluation) {}

}
