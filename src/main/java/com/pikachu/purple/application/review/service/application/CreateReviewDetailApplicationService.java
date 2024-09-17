package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.mood.service.domain.MoodDomainService;
import com.pikachu.purple.application.perfume.port.in.GetPerfumeByIdUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.review.port.in.CreateReviewDetailUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.Mood;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReviewDetailApplicationService implements CreateReviewDetailUseCase {

    private final GetPerfumeByIdUseCase getPerfumeByIdUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final StarRatingDomainService starRatingDomainService;
    private final ReviewDomainService reviewDomainService;
    private final MoodDomainService moodDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(new GetUserByIdUseCase.Command(userId));
        GetPerfumeByIdUseCase.Result perfume = getPerfumeByIdUseCase.invoke(new GetPerfumeByIdUseCase.Command(
            command.perfumeId()));

        StarRating starRating = starRatingDomainService.create(
            user.user(),
            perfume.perfume(),
            command.score()
        );

        Set<Mood> moods = moodDomainService.findAllByNames(command.moodNames());

        ReviewEvaluation reviewEvaluation = convertToReviewEvaluation(command.evaluationFieldVOs());

        reviewDomainService.createDetail(
            user.user(),
            perfume.perfume(),
            command.content(),
            ReviewType.DETAIL,
            starRating,
            moods,
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

    private EvaluationField<EvaluationOption> convertToEvaluationField(EvaluationFieldVO evaluationFieldVO) {
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
