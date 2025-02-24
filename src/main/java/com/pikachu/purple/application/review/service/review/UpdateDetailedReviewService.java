package com.pikachu.purple.application.review.service.review;

import com.pikachu.purple.application.review.port.in.review.UpdateDetailedReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.application.review.util.ReviewEvaluationConverter;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.DecreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UpdateDetailedReviewService implements UpdateDetailedReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final ReviewEvaluationRepository reviewEvaluationRepository;

    private final CreateReviewEvaluationUseCase createReviewEvaluationUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @Transactional
    @Override
    public void update(
        Long reviewId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    ) {
        Review review = reviewRepository.findWithPerfume(reviewId);

        if(review.getType() == ReviewType.SIMPLE) {
            createReviewEvaluationUseCase.create(
                review,
                evaluationFieldVOs
            );

            reviewRepository.createReviewMoods(
                review.getId(),
                moodNames
            );
        }

        else{
            ReviewEvaluation beforeReviewEvaluation = reviewEvaluationRepository.find(reviewId);

            decreaseEvaluationStatisticUseCase.invoke(
                review.getPerfume().getId(),
                beforeReviewEvaluation
            );

            ReviewEvaluation afterReviewEvaluation = ReviewEvaluationConverter.of(
                reviewId,
                evaluationFieldVOs
            );
            reviewEvaluationRepository.update(
                afterReviewEvaluation
            );

            increaseEvaluationStatisticUseCase.invoke(
                review.getPerfume().getId(),
                afterReviewEvaluation
            );

            reviewRepository.updateReviewMood(
                reviewId,
                moodNames
            );
        }

        updateReviewUseCase.update(
            reviewId,
            review.getPerfume().getId(),
            ReviewType.DETAIL,
            content,
            score
        );

    }

}
