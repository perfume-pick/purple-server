package com.pikachu.purple.application.statistic.service.application.evaluationstatistic;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsDetailWithEvaluationUseCase;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.RecountEvaluationStatisticsUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecountEvaluationStatisticsApplicationService implements
    RecountEvaluationStatisticsUseCase {

    private final EvaluationStatisticDomainService evaluationStatisticDomainService;
    private final GetReviewsDetailWithEvaluationUseCase getReviewsDetailWithEvaluationUseCase;
    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;

    @Override
    public void invoke() {
        EvaluationStatistic evaluationStatistic = new EvaluationStatistic();

        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();
        perfumeIds.forEach(evaluationStatistic::setDefault);

        List<Review> reviews = getReviewsDetailWithEvaluationUseCase.invoke()
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

        evaluationStatisticDomainService.updateAll(evaluationStatistic);
    }

}
