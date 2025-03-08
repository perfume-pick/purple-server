package com.pikachu.purple.application.statistic.service;

import com.pikachu.purple.application.statistic.port.in.GetPerfumeStatisticUseCase;
import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.application.statistic.port.out.StarRatingStatisticRepository;
import com.pikachu.purple.application.util.MathUtil;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class GetPerfumeStatisticService implements
    GetPerfumeStatisticUseCase {

    private final EvaluationStatisticRepository evaluationStatisticRepository;
    private final StarRatingStatisticRepository starRatingStatisticRepository;

    @Override
    public Result findByPerfumeId(Long perfumeId) {
        EvaluationStatistic evaluationStatistic = evaluationStatisticRepository.findByPerfumeIdOrderByVotesDesc(
            perfumeId);

        List<EvaluationField<EvaluationOptionStatistic>> evaluationFields = new ArrayList<>();
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

            List<EvaluationOptionStatistic> evaluationOptionStatistics = new ArrayList<>();
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
                evaluationOptionStatistics.add(
                    EvaluationOptionStatistic.of(
                        order,
                        evaluationOption,
                        votes,
                        totalVotesByField
                    )
                );
            }

            EvaluationField<EvaluationOptionStatistic> evaluationFieldDTO = EvaluationField.of(
                evaluationField,
                evaluationOptionStatistics
            );

            evaluationFields.add(evaluationFieldDTO);
        }

        List<StarRatingStatistic> starRatingStatistics = starRatingStatisticRepository
            .findAllByPerfumeId(perfumeId);
        int totalVotes = starRatingStatistics.stream().mapToInt(StarRatingStatistic::getVotes)
            .sum();

        List<StarRatingStatisticDTO> starRatingStatisticDTOs = starRatingStatistics.stream()
            .map(starRatingStatistic -> this.mapToStarRatingStatisticDTO(
                starRatingStatistic,
                totalVotes
            ))
            .toList();

        return new Result(
            starRatingStatisticDTOs,
            evaluationFields
        );
    }

    public StarRatingStatisticDTO mapToStarRatingStatisticDTO(
        StarRatingStatistic starRatingStatistic,
        int totalVotes
    ) {
        return new StarRatingStatisticDTO(
            starRatingStatistic.getScore(),
            MathUtil.getPercentage(
                starRatingStatistic.getVotes(),
                totalVotes
            )
        );
    }
}
