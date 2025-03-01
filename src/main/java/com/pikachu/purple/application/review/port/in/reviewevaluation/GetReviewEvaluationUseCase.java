package com.pikachu.purple.application.review.port.in.reviewevaluation;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface GetReviewEvaluationUseCase {

    Result find();

    Result find(Review review);

    Result find(Perfume perfume);

    record Result(ReviewEvaluation reviewEvaluation) {}

}
