package com.pikachu.purple.application.review.service.application.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetReviewEvaluationService implements GetReviewEvaluationUseCase {

    private final ReviewEvaluationRepository reviewEvaluationRepository;

    @Override
    public Result find() {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find();

        return new Result(reviewEvaluation);
    }

    @Override
    public Result find(Long reviewId) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find(reviewId);

        return new Result(reviewEvaluation);
    }

}
