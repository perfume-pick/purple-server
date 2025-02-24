package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateSimpleReviewUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.DecreaseEvaluationStatisticUseCase;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateSimpleReviewService implements UpdateSimpleReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewEvaluationRepository reviewEvaluationRepository;

    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @Transactional
    @Override
    public void update(
        Long reviewId,
        int score,
        String content
    ) {
        Review review = reviewRepository.findWithPerfume(reviewId);

        if(review.getType() == ReviewType.DETAIL) {
            ReviewEvaluation reviewEvaluation = reviewEvaluationRepository.find(reviewId);
            reviewEvaluationRepository.delete(reviewId);
            decreaseEvaluationStatisticUseCase.invoke(
                review.getPerfume().getId(),
                reviewEvaluation
            );
            reviewRepository.deleteReviewMoods(reviewId);
        }

        updateReviewUseCase.update(
            reviewId,
            review.getPerfume().getId(),
            ReviewType.SIMPLE,
            content,
            score
        );
    }

}
