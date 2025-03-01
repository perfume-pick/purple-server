package com.pikachu.purple.application.statistic.service.evaluationstatistic;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
import com.pikachu.purple.application.review.port.in.review.GetReviewsUseCase;
import com.pikachu.purple.application.review.port.in.reviewevaluation.GetReviewEvaluationUseCase;
import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.RecountEvaluationStatisticsUseCase;
import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RecountEvaluationStatisticsService implements
    RecountEvaluationStatisticsUseCase {

    private final GetReviewsUseCase getReviewsUseCase;
    private final GetReviewEvaluationUseCase getReviewEvaluationUseCase;
    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;

    private final EvaluationStatisticRepository evaluationStatisticRepository;

    @Override
    public void invoke() {
        EvaluationStatistic evaluationStatistic = new EvaluationStatistic();

        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();
        perfumeIds.forEach(evaluationStatistic::setDefault);

        List<Review> reviews = getReviewsUseCase.findAll(ReviewType.DETAIL).reviews();

        ReviewEvaluation reviewEvaluation = getReviewEvaluationUseCase.find().reviewEvaluation();

        reviews.forEach(
            review -> reviewEvaluation.getFields(review.getId()).forEach(
                fieldType -> reviewEvaluation.getOptions(review.getId(), fieldType).forEach(
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
