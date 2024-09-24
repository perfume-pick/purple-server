package com.pikachu.purple.application.review.port.in.reviewevaluation;


import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface GetReviewEvaluationUseCase {

    Result invoke(Command command);

    record Command(Long reviewId) {}

    record Result(ReviewEvaluation reviewEvaluation) {}

}
