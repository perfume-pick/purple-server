package com.pikachu.purple.application.evaluation.service.application;

import com.pikachu.purple.application.evaluation.common.dto.FragranticaEvaluationDTO;
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
        List<FragranticaEvaluationDTO> fragranticaEvaluationDTOs = new ArrayList<>();

        for (EvaluationField evaluationField : EvaluationField.values()) {
            if (evaluationField == EvaluationField.SEASON_TIME) {

                FragranticaEvaluationDTO fragranticaEvaluationDTO = findResultByEachField(
                    command.perfumeId(),
                    evaluationField,
                    3
                );

                fragranticaEvaluationDTOs.add(fragranticaEvaluationDTO);

            } else {
                FragranticaEvaluationDTO fragranticaEvaluationDTO = findResultByEachField(
                    command.perfumeId(),
                    evaluationField,
                    1
                );

                fragranticaEvaluationDTOs.add(fragranticaEvaluationDTO);
            }
        }
        return new Result(fragranticaEvaluationDTOs);
    }

    private FragranticaEvaluationDTO findResultByEachField(Long perfumeId, EvaluationField field, int maxSize) {
        List<FragranticaEvaluation> fragranticaEvaluations =
            fragranticaEvaluationDomainService.findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
                perfumeId,
                field.getCode(),
                maxSize
            );

        List<MostVotedOption> mostVotedOptions =
            fragranticaEvaluations.stream().map(MostVotedOption::from).toList();

        return FragranticaEvaluationDTO.of(
            field,
            mostVotedOptions
        );
    }
}
