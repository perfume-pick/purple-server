package com.pikachu.purple.application.userevaluation.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.userevaluation.port.in.CreateReviewEvaluationUseCase;
import com.pikachu.purple.application.userevaluation.service.domain.ReviewEvaluationDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReviewEvaluationApplicationService implements CreateReviewEvaluationUseCase {

    private final ReviewEvaluationDomainService reviewEvaluationDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        reviewEvaluationDomainService.create(
            userId,
            command.perfumeId(),
            command.evaluationFieldVOs()
        );

    }

}
