package com.pikachu.purple.application.evaluation.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.evaluation.port.in.EvaluationMoodCreateUseCase;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationMoodDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationMoodCreateApplicationService implements EvaluationMoodCreateUseCase {

    private final EvaluationMoodDomainService evaluationMoodDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        evaluationMoodDomainService.create(
            userId,
            command.perfumeId(),
            command.moods()
        );
    }

}
