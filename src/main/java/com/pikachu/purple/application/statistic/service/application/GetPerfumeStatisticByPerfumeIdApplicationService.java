package com.pikachu.purple.application.statistic.service.application;

import com.pikachu.purple.application.statistic.common.dto.StarRatingStatisticDTO;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticByPerfumeIdUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import com.pikachu.purple.util.DateUtil;
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
        String yesterday = DateUtil.yesterday();
        EvaluationStatistic evaluationStatistic = evaluationStatisticDomainService.findOrderByVotesDesc(
            yesterday,
            command.perfumeId()
        );

        List<EvaluationFieldDTO<EvaluationOptionStatisticDTO>> evaluationFieldDTOs = new ArrayList<>();
        for (EvaluationFieldType evaluationField : evaluationStatistic.getFields(
            command.perfumeId())) {
            int totalVotesByField = evaluationStatistic.getOptions(
                    command.perfumeId(),
                    evaluationField
                ).stream()
                .mapToInt(
                    evaluationOption -> evaluationStatistic.getVotes(
                        command.perfumeId(),
                        evaluationField,
                        evaluationOption
                    )
                ).sum();

            List<EvaluationOptionStatisticDTO> evaluationOptionStatisticDTOs = new ArrayList<>();
            List<EvaluationOptionType> evaluationOptions =
                evaluationStatistic.getOptions(
                    command.perfumeId(),
                    evaluationField
                );
            for (int i = 0; i < evaluationOptions.size(); i++) {
                int order = i + 1;

                EvaluationOptionType evaluationOption = evaluationOptions.get(i);
                int votes = evaluationStatistic.getVotes(
                    command.perfumeId(),
                    evaluationField,
                    evaluationOption
                );
                evaluationOptionStatisticDTOs.add(
                    EvaluationOptionStatisticDTO.of(
                        order,
                        evaluationOption,
                        votes,
                        totalVotesByField
                    )
                );
            }

            EvaluationFieldDTO<EvaluationOptionStatisticDTO> evaluationFieldDTO = EvaluationFieldDTO.of(
                evaluationField,
                evaluationOptionStatisticDTOs
            );

            evaluationFieldDTOs.add(evaluationFieldDTO);
        }

        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticDomainService.findAll(
//            yesterday,
            command.perfumeId()
        );
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
