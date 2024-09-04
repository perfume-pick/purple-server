package com.pikachu.purple.application.evaluation.service.application;

import com.pikachu.purple.application.evaluation.common.dto.FragranticaEvaluationDto;
import com.pikachu.purple.application.evaluation.common.dto.MostVotedOption;
import com.pikachu.purple.application.evaluation.port.in.FragranticaEvaluationGetByPerfumeIdUseCase;
import com.pikachu.purple.application.evaluation.service.domain.FragranticaEvaluationDomainService;
import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;
import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FragranticaEvaluationGetByPerfumeIdApplicationService implements
    FragranticaEvaluationGetByPerfumeIdUseCase {

    private final FragranticaEvaluationDomainService fragranticaEvaluationDomainService;

    @Override
    public Result invoke(Command command) {
        List<FragranticaEvaluationDto> fragranticaEvaluationDtos = new ArrayList<>();

        for (EvaluationField evaluationField : EvaluationField.values()) {
            if (evaluationField == EvaluationField.SEASON_TIME) {

                FragranticaEvaluationDto fragranticaEvaluationDto = findResultByEachField(
                    command.perfumeId(),
                    evaluationField,
                    3
                );

                fragranticaEvaluationDtos.add(fragranticaEvaluationDto);

            } else {
                FragranticaEvaluationDto fragranticaEvaluationDto = findResultByEachField(
                    command.perfumeId(),
                    evaluationField,
                    1
                );

                fragranticaEvaluationDtos.add(fragranticaEvaluationDto);
            }
        }
        return new Result(fragranticaEvaluationDtos);
    }

    private FragranticaEvaluationDto findResultByEachField(Long perfumeId, EvaluationField field, int maxSize) {
        List<FragranticaEvaluation> fragranticaEvaluations =
            fragranticaEvaluationDomainService.findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
                perfumeId,
                field.getCode(),
                maxSize
            );

        List<MostVotedOption> mostVotedOptions =
            fragranticaEvaluations.stream().map(MostVotedOption::from).toList();

        return FragranticaEvaluationDto.of(
            field,
            mostVotedOptions
        );
    }
}
