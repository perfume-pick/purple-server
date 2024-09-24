package com.pikachu.purple.application.review.service.application.reviewevaluation;

import com.pikachu.purple.application.review.port.in.reviewevaluation.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReviewEvaluationApplicationService implements CreateReviewEvaluationUseCase {

    private final ReviewEvaluationDomainService reviewEvaluationDomainService;
    private final IncreaseEvaluationStatisticUseCase increaseEvaluationStatisticUseCase;

    @Override
    public void invoke(Command command) {

        Review review = command.review();
        ReviewEvaluation reviewEvaluation = convertToReviewEvaluation(command.evaluationFieldVOs());

        reviewEvaluationDomainService.create(
            review.getId(),
            reviewEvaluation
        );

        increaseEvaluationStatisticUseCase.invoke(
            new IncreaseEvaluationStatisticUseCase.Command(
                review.getPerfume().getId(),
                reviewEvaluation
            )
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
