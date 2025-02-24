package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateSimpleReviewUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
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
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @Transactional
    @Override
    public void invoke(
        Long reviewId,
        int score,
        String content
    ) {
        Review review = reviewRepository.findWithPerfume(reviewId);

        if(review.getType() == ReviewType.DETAIL) {
            ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.findAll(reviewId);
            reviewEvaluationDomainService.deleteAll(reviewId);
            decreaseEvaluationStatisticUseCase.invoke(
                review.getPerfume().getId(),
                reviewEvaluation
            );
            reviewRepository.deleteReviewMoods(reviewId);
        }

        updateReviewUseCase.invoke(
            reviewId,
            review.getPerfume().getId(),
            ReviewType.SIMPLE,
            content,
            score
        );
    }

}
