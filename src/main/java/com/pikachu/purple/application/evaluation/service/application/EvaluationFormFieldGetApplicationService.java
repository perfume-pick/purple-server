package com.pikachu.purple.application.evaluation.service.application;

import com.pikachu.purple.application.evaluation.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.application.evaluation.common.dto.EvaluationOptionDTO;
import com.pikachu.purple.application.evaluation.port.in.EvaluationFormFieldGetUseCase;
import com.pikachu.purple.application.mood.port.in.MoodGetUseCase;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationFormFieldGetApplicationService implements
    EvaluationFormFieldGetUseCase {

    private final MoodGetUseCase moodGetUseCase;

    @Override
    public Result invoke() {
        List<EvaluationFieldDTO> evaluationFieldDTOs = Stream.of(EvaluationFieldType.values())
            .map(field -> EvaluationFieldDTO.of(
                field.getCode(),
                field.getName(),
                field.getEvaluationOptionTypes().stream()
                    .map(option -> EvaluationOptionDTO.of(
                        option.getCode(),
                        option.getName()
                    ))
                    .toList()
            ))
            .toList();

        MoodGetUseCase.Result moodResult = moodGetUseCase.invoke();

        return new Result(
            evaluationFieldDTOs,
            moodResult.moods()
        );
    }

}
