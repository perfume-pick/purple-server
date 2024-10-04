package com.pikachu.purple.application.review.service.application.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetReviewEvaluationApplicationService implements GetReviewEvaluationUseCase {

    private final ReviewEvaluationDomainService reviewEvaluationDomainService;

    @Override
    public Result invoke(Command command) {
        ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.find(
            command.reviewId());

        return new Result(reviewEvaluation);
    }

}
