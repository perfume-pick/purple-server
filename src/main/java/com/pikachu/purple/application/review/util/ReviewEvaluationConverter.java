package com.pikachu.purple.application.review.util;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReviewEvaluationConverter {

    public static ReviewEvaluation of(Long reviewId, List<EvaluationFieldVO> evaluationFieldVOs) {
        ReviewEvaluation reviewEvaluation = new ReviewEvaluation();

        for (EvaluationFieldVO evaluationFieldVO : evaluationFieldVOs) {
            EvaluationFieldType field = EvaluationFieldType.of(evaluationFieldVO.fieldCode());
            List<EvaluationOptionType> options = evaluationFieldVO.optionCodes().stream()
                .map(EvaluationOptionType::of)
                .toList();

            reviewEvaluation.add(reviewId, field, options);
        }

        return reviewEvaluation;
    }

}
