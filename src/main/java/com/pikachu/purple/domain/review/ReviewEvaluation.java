package com.pikachu.purple.domain.review;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEvaluation {

    private List<EvaluationField<EvaluationOption>> fields;

    public static ReviewEvaluation from(List<EvaluationFieldVO> evaluationFieldVOs) {
        List<EvaluationField<EvaluationOption>> evaluationFields = evaluationFieldVOs.stream()
            .map(ReviewEvaluation::convertToEvaluationField)
            .toList();

        return ReviewEvaluation.builder()
            .fields(evaluationFields)
            .build();
    }

    private static EvaluationField<EvaluationOption> convertToEvaluationField(EvaluationFieldVO evaluationFieldVO) {
        EvaluationFieldType fieldType = EvaluationFieldType.of(evaluationFieldVO.fieldCode());

        List<EvaluationOption> options = evaluationFieldVO.optionCodes().stream()
            .map(ReviewEvaluation::convertToEvaluationOption)
            .toList();

        return EvaluationField.<EvaluationOption>builder()
            .type(fieldType)
            .options(options)
            .build();
    }

    private static EvaluationOption convertToEvaluationOption(String optionCode) {
        EvaluationOptionType optionType = EvaluationOptionType.of(optionCode);

        return EvaluationOption.builder()
            .type(optionType)
            .build();
    }

}
