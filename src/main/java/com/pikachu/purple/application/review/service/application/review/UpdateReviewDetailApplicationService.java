package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
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
public class UpdateReviewDetailApplicationService implements UpdateReviewDetailUseCase {

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final CreateReviewEvaluationUseCase createReviewEvaluationUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @Transactional
    @Override
    public void invoke(
        Long reviewId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    ) {
        Review review = reviewDomainService.findWithPerfume(reviewId);

        if(review.getType() == ReviewType.SIMPLE) {
            createReviewEvaluationUseCase.invoke(
                new CreateReviewEvaluationUseCase.Command(
                    review,
                    evaluationFieldVOs
                )
            );

            reviewDomainService.createReviewMoods(
                review.getId(),
                moodNames
            );
        }

        else{
            ReviewEvaluation beforeReviewEvaluation = reviewEvaluationDomainService.findAll(
                reviewId);

            decreaseEvaluationStatisticUseCase.invoke(new DecreaseEvaluationStatisticUseCase.Command(
                    review.getPerfume().getId(),
                    beforeReviewEvaluation
                )
            );

            ReviewEvaluation afterReviewEvaluation = ReviewEvaluationConverter.of(
                reviewId,
                evaluationFieldVOs
            );
            reviewEvaluationDomainService.updateAll(
                afterReviewEvaluation
            );

            increaseEvaluationStatisticUseCase.invoke(new IncreaseEvaluationStatisticUseCase.Command(
                review.getPerfume().getId(),
                afterReviewEvaluation
            ));

            reviewDomainService.updateReviewMood(
                reviewId,
                moodNames
            );
        }

        updateReviewUseCase.invoke(new UpdateReviewUseCase.Command(
                reviewId,
                review.getPerfume().getId(),
                ReviewType.DETAIL,
                content,
                score
            )
        );

    }

}
