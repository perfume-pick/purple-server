package com.pikachu.purple.application.perfume.service.application;

import static com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType.*;

import com.pikachu.purple.application.evaluation.common.dto.MostVotedOption;
import com.pikachu.purple.application.evaluation.service.domain.FragranticaEvaluationDomainService;
import com.pikachu.purple.application.perfume.common.dto.EvaluationFieldDTO;
import com.pikachu.purple.application.perfume.common.dto.EvaluationOptionDTO;
import com.pikachu.purple.application.perfume.port.in.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.perfume.FragranticaEvaluation;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFragranticaEvaluationByPerfumeIdApplicationService implements
    GetFragranticaEvaluationByPerfumeIdUseCase {

    private final FragranticaEvaluationDomainService fragranticaEvaluationDomainService;

    @Override
    public Result invoke(Command command) {

        FragranticaEvaluation fragranticaEvaluation = fragranticaEvaluationDomainService.findByPerfumeIdOrderByVotesDesc(
            command.perfumeId());

        List<EvaluationFieldDTO> evaluationFieldDTOs = new ArrayList<>();
        for (EvaluationField<EvaluationOptionStatistic> evaluationField : fragranticaEvaluation.getFields()) {
            int totalVotesByField = evaluationField.getOptions().stream()
                .mapToInt(EvaluationOptionStatistic::getVotes).sum();

            int maxSize = evaluationField.getType().is(SEASON_TIME) ? 3 : 0;

            List<EvaluationOptionDTO> evaluationOptionDTOs = new ArrayList<>();
            for (int i=0; i < maxSize; i++) {
                List<EvaluationOptionStatistic> optionStatistics = evaluationField.getOptions();
                evaluationOptionDTOs.add(
                    EvaluationOptionDTO.of(
                        optionStatistics.get(i),
                        totalVotesByField
                    )
                );
            }
            EvaluationFieldDTO evaluationFieldDTO = EvaluationFieldDTO.of(
                evaluationField.getType(),
                evaluationOptionDTOs
            );

            evaluationFieldDTOs.add(evaluationFieldDTO);
        }
        return new Result(evaluationFieldDTOs);
    }

}
