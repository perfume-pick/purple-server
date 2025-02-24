package com.pikachu.purple.application.statistic.service.evaluationstatistic;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetDetailedReviewsUseCase;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.RecountEvaluationStatisticsUseCase;
import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RecountEvaluationStatisticsService implements
    RecountEvaluationStatisticsUseCase {

    private final EvaluationStatisticRepository evaluationStatisticRepository;
    private final GetDetailedReviewsUseCase getDetailedReviewsUseCase;
    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;

    @Override
    public void invoke() {
        EvaluationStatistic evaluationStatistic = new EvaluationStatistic();

        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();
        perfumeIds.forEach(evaluationStatistic::setDefault);

        List<Review> reviews = getDetailedReviewsUseCase.findAll()
            .reviews();

        reviews.forEach(
            review -> review.getEvaluation().getFields(review.getId()).forEach(
                fieldType -> review.getEvaluation().getOptions(review.getId(), fieldType).forEach(
                    optionType -> evaluationStatistic.increase(
                        review.getPerfume().getId(),
                        fieldType,
                        optionType
                    )
                )
            )
        );

        evaluationStatisticRepository.updateAll(evaluationStatistic);
    }

}
