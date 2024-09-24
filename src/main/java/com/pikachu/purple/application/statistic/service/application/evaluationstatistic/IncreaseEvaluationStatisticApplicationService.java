package com.pikachu.purple.application.statistic.service.application.evaluationstatistic;

import com.pikachu.purple.application.statistic.port.in.evaluationstatistic.IncreaseEvaluationStatisticUseCase;
import com.pikachu.purple.application.statistic.service.domain.EvaluationStatisticDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncreaseEvaluationStatisticApplicationService implements
    IncreaseEvaluationStatisticUseCase {

    private final EvaluationStatisticDomainService evaluationStatisticDomainService;

    @Override
    public void invoke(Command command) {
        for (EvaluationField<EvaluationOption> field: command.reviewEvaluation().getFields()) {
            for (EvaluationOption option : field.getOptions()) {
                evaluationStatisticDomainService.increaseVotes(
                    command.perfumeId(),
                    field.getType().getCode(),
                    option.getType().getCode()
                );
            }
        }
    }

}
