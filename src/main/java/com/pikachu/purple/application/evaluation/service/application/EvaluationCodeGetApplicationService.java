package com.pikachu.purple.application.evaluation.service.application;

import com.pikachu.purple.application.evaluation.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.application.evaluation.common.dto.EvaluationOptionDTO;
import com.pikachu.purple.application.evaluation.port.in.EvaluationCodeGetUseCase;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationCodeDomainService;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationFieldOptionDomainService;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationMoodDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationCode;
import com.pikachu.purple.domain.evaluation.EvaluationFieldOption;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationCodeGetApplicationService implements EvaluationCodeGetUseCase {

    private final EvaluationCodeDomainService evaluationCodeDomainService;
    private final EvaluationFieldOptionDomainService evaluationFieldOptionDomainService;
    private final EvaluationMoodDomainService evaluationMoodDomainService;

    @Override
    public Result invoke() {
        List<EvaluationCode> evaluationCodes = evaluationCodeDomainService.findAll();
        List<EvaluationFieldOption> evaluationFieldOptions = evaluationFieldOptionDomainService.findAll();
        List<EvaluationMood> evaluationMoods = evaluationMoodDomainService.findAll();

        Map<String, String> codeMap = evaluationCodes.stream()
            .collect(Collectors.toMap(
                    EvaluationCode::getCode,
                    EvaluationCode::getName
                ));

        Map<String, List<EvaluationFieldOption>> groupByFieldMap = evaluationFieldOptions.stream()
            .collect(Collectors.groupingBy(EvaluationFieldOption::getFieldCode));

        List<EvaluationFieldDTO> evaluationFieldDTOs = groupByFieldMap.entrySet().stream()
            .map(entry -> EvaluationFieldDTO.from(
                entry.getKey(),
                codeMap.get(entry.getKey()),
                entry.getValue().stream()
                    .map(option -> EvaluationOptionDTO.from(
                        option.getOptionCode(),
                        codeMap.get(option.getOptionCode())
                    ))
                    .toList()
            ))
            .toList();

        return new Result(
            evaluationFieldDTOs,
            evaluationMoods
        );
    }

}
