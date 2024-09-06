package com.pikachu.purple.application.userevaluation.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.userevaluation.port.in.UserEvaluationCreateUseCase;
import com.pikachu.purple.application.userevaluation.service.domain.UserEvaluationDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEvaluationCreateApplicationService implements UserEvaluationCreateUseCase {

    private final UserEvaluationDomainService userEvaluationDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        userEvaluationDomainService.create(
            userId,
            command.perfumeId(),
            command.evaluationFieldVOs()
        );

    }

}
