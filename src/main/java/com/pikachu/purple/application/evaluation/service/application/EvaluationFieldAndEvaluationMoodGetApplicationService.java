package com.pikachu.purple.application.evaluation.service.application;

import com.pikachu.purple.application.evaluation.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.application.evaluation.common.dto.EvaluationOptionDTO;
import com.pikachu.purple.application.evaluation.port.in.EvaluationFieldAndEvaluationMoodGetUseCase;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationMoodDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationFieldAndEvaluationMoodGetApplicationService implements
    EvaluationFieldAndEvaluationMoodGetUseCase {

    private final EvaluationMoodDomainService evaluationMoodDomainService;

    @Override
    public Result invoke() {
        List<EvaluationFieldDTO> evaluationFieldDTOs = Stream.of(EvaluationField.values())
            .map(field -> EvaluationFieldDTO.of(
                field.getCode(),
                field.getName(),
                field.getEvaluationOptions().stream()
                    .map(option -> EvaluationOptionDTO.of(
                        option.getCode(),
                        option.getName()
                    ))
                    .toList()
            ))
            .toList();

        List<EvaluationMood> evaluationMoods = evaluationMoodDomainService.findAll();

        return new Result(
            evaluationFieldDTOs,
            evaluationMoods
        );
    }

}
