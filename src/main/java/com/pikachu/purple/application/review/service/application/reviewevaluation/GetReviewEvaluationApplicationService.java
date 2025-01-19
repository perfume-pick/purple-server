package com.pikachu.purple.application.review.service.application.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetReviewEvaluationApplicationService implements GetReviewEvaluationUseCase {

    private final ReviewEvaluationDomainService reviewEvaluationDomainService;

    @Override
    public Result invoke() {
        ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.findAll();

        return new Result(reviewEvaluation);
    }

    @Override
    public Result invoke(Long reviewId) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.findAll(reviewId);

        return new Result(reviewEvaluation);
    }

}
