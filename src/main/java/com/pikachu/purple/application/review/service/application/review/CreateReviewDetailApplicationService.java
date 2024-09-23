package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.CreateReviewDetailUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReviewDetailApplicationService implements CreateReviewDetailUseCase {

    private final ReviewDomainService reviewDomainService;
    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final CreateStarRatingUseCase createStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {

        Long userId = getCurrentUserAuthentication().userId();

        GetStarRatingUseCase.Result getStarRatingResult = getStarRatingUseCase.invoke(
            new GetStarRatingUseCase.Command(
                userId,
                command.perfumeId()
            )
        );

        StarRating starRating;
        if (getStarRatingResult.starRating() == null) {
            CreateStarRatingUseCase.Result createStarRatingResult = createStarRatingUseCase.invoke(
                new CreateStarRatingUseCase.Command(
                    command.perfumeId(),
                    command.score()
                )
            );
            starRating = createStarRatingResult.starRating();
        } else {
            StarRating previousStarRating = getStarRatingResult.starRating();
            UpdateStarRatingUseCase.Result updateStarRatingResult = updateStarRatingUseCase.invoke(
                new UpdateStarRatingUseCase.Command(
                    previousStarRating.getPerfume().getId(),
                    previousStarRating.getScore(),
                    command.score()
                )
            );
            starRating = updateStarRatingResult.starRating();
        }

        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        Review review = reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            command.content(),
            ReviewType.DETAIL
        );

        reviewDomainService.createReviewMoods(
            review.getId(),
            command.moodNames()
        );

        ReviewEvaluation reviewEvaluation = convertToReviewEvaluation(command.evaluationFieldVOs());
        reviewEvaluationDomainService.create(
            review.getId(),
            reviewEvaluation
        );
    }

    private ReviewEvaluation convertToReviewEvaluation(List<EvaluationFieldVO> evaluationFieldVOs) {
        List<EvaluationField<EvaluationOption>> evaluationFields = evaluationFieldVOs.stream()
            .map(this::convertToEvaluationField)
            .toList();

        return ReviewEvaluation.builder()
            .fields(evaluationFields)
            .build();
    }

    private EvaluationField<EvaluationOption> convertToEvaluationField(
        EvaluationFieldVO evaluationFieldVO) {

        EvaluationFieldType fieldType = EvaluationFieldType.of(evaluationFieldVO.fieldCode());

        List<EvaluationOption> options = evaluationFieldVO.optionCodes().stream()
            .map(this::convertToEvaluationOption)
            .toList();

        return EvaluationField.<EvaluationOption>builder()
            .type(fieldType)
            .options(options)
            .build();
    }

    private EvaluationOption convertToEvaluationOption(String optionCode) {
        EvaluationOptionType optionType = EvaluationOptionType.of(optionCode);

        return EvaluationOption.builder()
            .type(optionType)
            .build();
    }


}
