package com.pikachu.purple.application.statistic.service.evaluationstatistic;

import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.statistic.port.out.EvaluationStatisticRepository;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import com.pikachu.purple.domain.review.ReviewEvaluation;
import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IncreaseEvaluationStatisticService implements
    IncreaseEvaluationStatisticUseCase {

    private final EvaluationStatisticRepository evaluationStatisticRepository;

    @Override
    public void invoke(
        Long perfumeId,
        ReviewEvaluation reviewEvaluation
    ) {
        reviewEvaluation.getReviewIdSet().forEach(
            reviewId -> reviewEvaluation.getFields(reviewId).forEach(
                field -> reviewEvaluation.getOptions(reviewId, field).forEach(
                    option -> this.increaseVotes(
                        perfumeId,
                        field,
                        option
                    )
                )
            )
        );
    }

    @DistributedLock(
        name = "EvaluationStatistic",
        key = "T(String).valueOf(#perfumeId).concat('-').concat(#field.code).concat('-').concat(#option.code)"
    )
    private void increaseVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        evaluationStatisticRepository.increaseVotes(
            perfumeId,
            field,
            option
        );
    }

}
