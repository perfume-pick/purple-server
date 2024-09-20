package com.pikachu.purple.application.evaluation.service.application;

import com.pikachu.purple.application.evaluation.port.in.GetEvaluationFormFieldUseCase;
import com.pikachu.purple.application.mood.port.in.GetMoodsUseCase;
import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionDTO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEvaluationFormFieldApplicationService implements
    GetEvaluationFormFieldUseCase {

    private final GetMoodsUseCase getMoodsUseCase;

    @Override
    public Result invoke() {
        List<EvaluationFieldDTO<EvaluationOptionDTO>> evaluationFieldDTOs = Stream.of(EvaluationFieldType.values())
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
