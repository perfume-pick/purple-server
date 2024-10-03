package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.DecreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.util.ReviewEvaluationUtil;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateReviewDetailApplicationService implements UpdateReviewDetailUseCase {

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;
    private final CreateReviewEvaluationUseCase createReviewEvaluationUseCase;
    private final DecreaseEvaluationStatisticUseCase decreaseEvaluationStatisticUseCase;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;

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

        if(review.getType() == ReviewType.SIMPLE) {
            reviewDomainService.updateReviewType(
                command.reviewId(),
                ReviewType.DETAIL
            );

            createReviewEvaluationUseCase.invoke(
                new CreateReviewEvaluationUseCase.Command(
                    review,
                    command.evaluationFieldVOs()
                )
            );

            reviewDomainService.createReviewMoods(
                review.getId(),
                command.moodNames()
            );
        }

        else{
            ReviewEvaluation beforeReviewEvaluation = reviewEvaluationDomainService.findByReviewId(
                command.reviewId());

            decreaseEvaluationStatisticUseCase.invoke(new DecreaseEvaluationStatisticUseCase.Command(
                    review.getPerfume().getId(),
                    beforeReviewEvaluation
                )
            );

            ReviewEvaluation afterReviewEvaluation = ReviewEvaluationUtil.from(command.evaluationFieldVOs());
            reviewEvaluationDomainService.update(
                command.reviewId(),
                afterReviewEvaluation
            );

            increaseEvaluationStatisticUseCase.invoke(new IncreaseEvaluationStatisticUseCase.Command(
                review.getPerfume().getId(),
                afterReviewEvaluation
            ));

            reviewDomainService.updateReviewMood(
                command.reviewId(),
                command.moodNames()
            );
        }

    }

}
