package com.pikachu.purple.application.userevaluation.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.userevaluation.port.in.UserEvaluationCreateUseCase;
import com.pikachu.purple.application.userevaluation.service.domain.UserEvaluationDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.review.vo.EvaluationForm;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEvaluationCreateApplicationService implements UserEvaluationCreateUseCase {

    private final UserEvaluationDomainService userEvaluationDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        List<Long> userEvaluationIds = command.evaluationForms().stream()
            .flatMap(evaluationForm -> IntStream.range(
                    0,
                    evaluationForm.optionCodes().size()
                )
                .mapToObj(i -> IdGenerator.generate()))
            .toList();

        userEvaluationDomainService.create(
            userEvaluationIds,
            userId,
            command.perfumeId(),
            command.evaluationForms()
        );


    }

}
