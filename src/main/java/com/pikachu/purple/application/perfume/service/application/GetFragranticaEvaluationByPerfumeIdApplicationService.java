package com.pikachu.purple.application.perfume.service.application;

import static com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType.SEASON_TIME;

import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationFieldDTO;
import com.pikachu.purple.application.perfume.common.dto.FragranticaEvaluationOptionDTO;
import com.pikachu.purple.application.perfume.port.in.GetFragranticaEvaluationByPerfumeIdUseCase;
import com.pikachu.purple.application.perfume.service.domain.FragranticaEvaluationDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
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

        List<FragranticaEvaluationFieldDTO> fragranticaEvaluationFieldDTOs = new ArrayList<>();
        for (EvaluationField<EvaluationOptionStatistic> evaluationField : fragranticaEvaluation.getFields()) {
            int totalVotesByField = evaluationField.getOptions().stream()
                .mapToInt(EvaluationOptionStatistic::getVotes).sum();

            int maxSize = evaluationField.getType().is(SEASON_TIME) ? 3 : 1;

            List<FragranticaEvaluationOptionDTO> fragranticaMostVotedOptionDTOs = new ArrayList<>();
            for (int i=0; i < maxSize; i++) {
                List<EvaluationOptionStatistic> optionStatistics = evaluationField.getOptions();
                fragranticaMostVotedOptionDTOs.add(
                    FragranticaEvaluationOptionDTO.of(
                        optionStatistics.get(i),
                        totalVotesByField
                    )
                );
            }
            FragranticaEvaluationFieldDTO fragranticaEvaluationFieldDTO = FragranticaEvaluationFieldDTO.of(
                evaluationField.getType(),
                fragranticaMostVotedOptionDTOs
            );

            fragranticaEvaluationFieldDTOs.add(fragranticaEvaluationFieldDTO);
        }
        return new Result(fragranticaEvaluationFieldDTOs);
    }

}
