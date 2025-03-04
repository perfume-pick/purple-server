package com.pikachu.purple.application.review.service.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.application.review.util.ReviewEvaluationConverter;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateReviewEvaluationService implements CreateReviewEvaluationUseCase {

    private final ReviewEvaluationRepository reviewEvaluationRepository;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;

    @Transactional
    @Override
    public void create(
        Review review,
        List<EvaluationFieldVO> evaluationFieldVOs
    ) {
        ReviewEvaluation reviewEvaluation = ReviewEvaluationConverter.of(
            review.getId(),
            evaluationFieldVOs
        );

        reviewEvaluationRepository.create(reviewEvaluation);

        increaseEvaluationStatisticUseCase.invoke(
            review.getPerfume().getId(),
            reviewEvaluation
        );
    }
    
}
