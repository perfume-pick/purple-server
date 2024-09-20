package com.pikachu.purple.application.statistic.service.application;

import com.pikachu.purple.application.statistic.port.in.IncreaseEvaluationStatisticUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncreaseEvaluationStatisticApplicationService implements
    IncreaseEvaluationStatisticUseCase {

    @Override
    public void invoke(Command command) {

    }
}
