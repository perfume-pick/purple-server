package com.pikachu.purple.application.statistic.service.application;

import com.pikachu.purple.application.statistic.common.dto.StarRatingStatisticDTO;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticByPerfumeIdUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerfumeStatisticByPerfumeIdApplicationService implements
    GetPerfumeStatisticByPerfumeIdUseCase {

    private final EvaluationStatisticDomainService evaluationStatisticDomainService;
    private final StarRatingStatisticDomainService starRatingStatisticDomainService;

    @Override
    public Result invoke(Command command) {
        EvaluationStatistic evaluationStatistic = evaluationStatisticDomainService.findByPerfumeIdOrderByVotesDesc(
            command.perfumeId());

        List<EvaluationFieldDTO<EvaluationOptionStatisticDTO>> evaluationFieldDTOs = new ArrayList<>();
        for (EvaluationField<EvaluationOptionStatistic> evaluationField : evaluationStatistic.getFields()) {
            int totalVotesByField = evaluationField.getOptions().stream()
                .mapToInt(EvaluationOptionStatistic::getVotes).sum();

            List<EvaluationOptionStatisticDTO> evaluationOptionStatisticDTOs = new ArrayList<>();
            List<EvaluationOptionStatistic> evaluationOptionStatistics = evaluationField.getOptions();
            for (int i = 0; i < evaluationOptionStatistics.size(); i++) {
                int order = i + 1;
                evaluationOptionStatisticDTOs.add(
                    EvaluationOptionStatisticDTO.of(
                        order,
                        evaluationOptionStatistics.get(i),
                        totalVotesByField
                    )
                );
            }

            EvaluationFieldDTO<EvaluationOptionStatisticDTO> evaluationFieldDTO = EvaluationFieldDTO.of(
                evaluationField.getType(),
                evaluationOptionStatisticDTOs
            );

            evaluationFieldDTOs.add(evaluationFieldDTO);
        }

        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticDomainService.findAllByPerfumeId(
            command.perfumeId());
        int totalVotes = starRatingStatistics.stream().mapToInt(StarRatingStatistic::getVotes)
            .sum();

        List<StarRatingStatisticDTO> starRatingStatisticDTOs = starRatingStatistics.stream()
            .map(starRatingStatistic -> StarRatingStatisticDTO.of(
                starRatingStatistic,
                totalVotes
            ))
            .toList();

        return new Result(
            starRatingStatisticDTOs,
            evaluationFieldDTOs
        );
    }
}
