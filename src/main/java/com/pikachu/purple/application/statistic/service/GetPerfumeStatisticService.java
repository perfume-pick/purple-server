package com.pikachu.purple.application.statistic.service;

import com.pikachu.purple.application.statistic.common.dto.StarRatingStatisticDTO;
import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticUseCase;
import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.domain.evaluation.dto.EvaluationFieldDTO;
import com.pikachu.purple.domain.evaluation.dto.EvaluationOptionStatisticDTO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPerfumeStatisticService implements
    GetPerfumeStatisticUseCase {

    private final EvaluationStatisticRepository evaluationStatisticRepository;
    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    public Result find(Long perfumeId) {
        EvaluationStatistic evaluationStatistic = evaluationStatisticRepository.findOrderByVotesDesc(
            perfumeId);

        List<EvaluationFieldDTO<EvaluationOptionStatisticDTO>> evaluationFieldDTOs = new ArrayList<>();
        for (EvaluationFieldType evaluationField : evaluationStatistic.getFields(
            perfumeId)) {
            int totalVotesByField = evaluationStatistic.getOptions(
                    perfumeId,
                    evaluationField
                ).stream()
                .mapToInt(
                    evaluationOption -> evaluationStatistic.getVotes(
                        perfumeId,
                        evaluationField,
                        evaluationOption
                    )
                ).sum();

            List<EvaluationOptionStatisticDTO> evaluationOptionStatisticDTOs = new ArrayList<>();
            List<EvaluationOptionType> evaluationOptions =
                evaluationStatistic.getOptions(
                    perfumeId,
                    evaluationField
                );
            for (int i = 0; i < evaluationOptions.size(); i++) {
                int order = i + 1;

                EvaluationOptionType evaluationOption = evaluationOptions.get(i);
                int votes = evaluationStatistic.getVotes(
                    perfumeId,
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

        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticRepository
            .findAll(perfumeId);
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
