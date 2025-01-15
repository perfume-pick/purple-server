package com.pikachu.purple.application.review.port.in.reviewevaluation;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface CreateReviewEvaluationUseCase {

    void invoke(
        Review review,
        List<EvaluationFieldVO> evaluationFieldVOs
    );

}
