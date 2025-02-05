package com.pikachu.purple.application.perfume.service.application.fragranticaevaluation;

import static com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType.SEASON_TIME;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationOptionDTO;
import com.pikachu.purple.application.perfume.port.in.fragranticaevaluation.GetFragranticaEvaluationUseCase;
import com.pikachu.purple.application.perfume.service.domain.FragranticaEvaluationDomainService;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.perfume.FragranticaEvaluation;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetFragranticaEvaluationService implements
    GetFragranticaEvaluationUseCase {

    private final FragranticaEvaluationDomainService fragranticaEvaluationDomainService;

    @Override
    public Result find(Long perfumeId) {

        FragranticaEvaluation fragranticaEvaluation = fragranticaEvaluationDomainService.findByPerfumeIdOrderByVotesDesc(
            perfumeId);

        List<FragranticaEvaluationFieldDTO> fragranticaEvaluationFieldDTOs = new ArrayList<>();
        for (EvaluationFieldType evaluationField : fragranticaEvaluation.getFieldSet()) {
            int totalVotesByField = fragranticaEvaluation.getOptions(evaluationField).stream()
                .mapToInt(
                    evaluationOption -> fragranticaEvaluation.getVotes(
                        evaluationField,
                        evaluationOption
                    )
                ).sum();

            int maxSize = evaluationField.is(SEASON_TIME) ? 3 : 1;

            List<FragranticaEvaluationOptionDTO> fragranticaMostVotedOptionDTOs = new ArrayList<>();
            List<EvaluationOptionType> evaluationOptions = fragranticaEvaluation.getOptions(
                evaluationField);
            for (int i = 0; i < maxSize; i++) {
                EvaluationOptionType evaluationOption = evaluationOptions.get(i);
                int votes = fragranticaEvaluation.getVotes(
                    evaluationField,
                    evaluationOption
                );
                fragranticaMostVotedOptionDTOs.add(
                    FragranticaEvaluationOptionDTO.of(
                        evaluationOption,
                        votes,
                        totalVotesByField
                    )
                );
            }
            FragranticaEvaluationFieldDTO fragranticaEvaluationFieldDTO = FragranticaEvaluationFieldDTO.of(
                evaluationField,
                fragranticaMostVotedOptionDTOs
            );

            fragranticaEvaluationFieldDTOs.add(fragranticaEvaluationFieldDTO);
        }
        return new Result(fragranticaEvaluationFieldDTOs);
    }

}
