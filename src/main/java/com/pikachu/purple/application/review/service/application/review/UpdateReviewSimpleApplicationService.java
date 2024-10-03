package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.DecreaseEvaluationStatisticUseCase;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateReviewSimpleApplicationService implements UpdateReviewSimpleUseCase {

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        Review review = reviewDomainService.updateContent(
            command.reviewId(),
            command.content()
        );

        GetStarRatingUseCase.Result starRatingResult = getStarRatingUseCase.invoke(
            new GetStarRatingUseCase.Command(
                userId,
                review.getPerfume().getId()
            )
        );

        StarRating previousStarRating = starRatingResult.starRating();

        updateStarRatingUseCase.invoke(
            new UpdateStarRatingUseCase.Command(
                previousStarRating.getPerfume().getId(),
                previousStarRating.getScore(),
                command.score()
            )
        );

        if(review.getType() == ReviewType.DETAIL) {
            reviewDomainService.updateReviewType(
                command.reviewId(),
                ReviewType.SIMPLE
            );

            ReviewEvaluation reviewEvaluation = reviewEvaluationDomainService.findByReviewId(command.reviewId());
            reviewEvaluationDomainService.deleteAll(command.reviewId());
            decreaseEvaluationStatisticUseCase.invoke(new DecreaseEvaluationStatisticUseCase.Command(
                    review.getPerfume().getId(),
                    reviewEvaluation
                )
            );
            reviewDomainService.deleteReviewMoods(command.reviewId());
        }
    }

}
