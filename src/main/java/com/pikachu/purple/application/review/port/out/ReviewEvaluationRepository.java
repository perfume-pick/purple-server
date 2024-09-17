package com.pikachu.purple.application.review.port.out;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import java.util.List;

public interface ReviewEvaluationRepository {

    void create(
        Long reviewId,
        ReviewEvaluation reviewEvaluation
    );
}
