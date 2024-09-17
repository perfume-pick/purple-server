package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.review.port.in.EvaluationFormFieldGetUseCase;
import com.pikachu.purple.application.mood.port.in.GetMoodsUseCase;
import com.pikachu.purple.application.perfume.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.application.perfume.common.dto.EvaluationOptionDTO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationFormFieldGetApplicationService implements
    EvaluationFormFieldGetUseCase {

    private final GetMoodsUseCase getMoodsUseCase;

    @Override
    public Result invoke() {
        List<EvaluationFieldDTO> evaluationFieldDTOs = Stream.of(EvaluationFieldType.values())
            .map(field -> EvaluationFieldDTO.of(
                field,
                field.getEvaluationOptionTypes().stream()
                    .map(EvaluationOptionDTO::from)
                    .toList()))
            .toList();

        GetMoodsUseCase.Result moodResult = getMoodsUseCase.invoke();

        return new Result(
            evaluationFieldDTOs,
            moodResult.moods()
        );
    }

}
