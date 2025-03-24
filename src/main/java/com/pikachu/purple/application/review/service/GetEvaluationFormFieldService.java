package com.pikachu.purple.application.review.service;

import com.pikachu.purple.application.review.port.in.GetEvaluationFormFieldUseCase;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.review.enums.Mood;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetEvaluationFormFieldService implements
    GetEvaluationFormFieldUseCase {

    @Transactional
    @Override
    public Result invoke() {
        List<EvaluationField<EvaluationOption>> evaluationFields = Stream.of(EvaluationFieldType.values())
            .map(field -> EvaluationField.of(
                field,
                field.getEvaluationOptionTypes().stream()
                    .map(EvaluationOption::from)
                    .toList()))
            .toList();

        List<Mood> moods = List.of(Mood.values());

        return new Result(
            evaluationFields,
            moods
        );
    }

}
