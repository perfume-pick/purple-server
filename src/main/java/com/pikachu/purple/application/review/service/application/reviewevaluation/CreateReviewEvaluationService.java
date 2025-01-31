package com.pikachu.purple.application.review.service.application.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.application.review.util.ReviewEvaluationConverter;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateReviewEvaluationService implements CreateReviewEvaluationUseCase {

    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;

    @Override
    public void invoke(
        Review review,
        List<EvaluationFieldVO> evaluationFieldVOs
    ) {
        ReviewEvaluation reviewEvaluation = ReviewEvaluationConverter.of(
            review.getId(),
            evaluationFieldVOs
        );

        reviewEvaluationDomainService.createAll(reviewEvaluation);

        increaseEvaluationStatisticUseCase.invoke(
            review.getPerfume().getId(),
            reviewEvaluation
        );
    }
    
}
