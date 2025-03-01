package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;

public interface ReviewEvaluationRepository {

    void create(ReviewEvaluation reviewEvaluation);

    ReviewEvaluation find();

    ReviewEvaluation find(Long reviewId);

    ReviewEvaluation find(Review review);

    ReviewEvaluation find(Perfume perfume);

    void delete(Long reviewId);

    void update(ReviewEvaluation reviewEvaluation);

}
