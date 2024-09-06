package com.pikachu.purple.application.userevaluation.service.domain.impl;

import com.pikachu.purple.application.userevaluation.port.out.UserEvaluationRepository;
import com.pikachu.purple.application.userevaluation.service.domain.UserEvaluationDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.review.vo.EvaluationForm;
import com.pikachu.purple.domain.evaluation.enums.EvaluationField;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOption;
import com.pikachu.purple.domain.user.entity.UserEvaluation;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEvaluationDomainServiceImpl implements UserEvaluationDomainService {

    private final UserEvaluationRepository userEvaluationRepository;

    @Override
    public void create(
        Long userId,
        Long perfumeId,
        List<EvaluationForm> evaluationForms
    ) {
        List<Long> userEvaluationIds = evaluationForms.stream()
            .flatMap(evaluationForm -> IntStream.range(
                0,
                    evaluationForm.optionCodes().size()
                )
                .mapToObj(i -> IdGenerator.generate()))
            .toList();


        List<UserEvaluation> userEvaluations = evaluationForms.stream()
            .flatMap(evaluationForm -> {
                int evaluationFormIndex = evaluationForms.indexOf(evaluationForm);
                return IntStream.range(
                    0,
                        evaluationForm.optionCodes().size()
                    )
                    .mapToObj(optionIndex -> UserEvaluation.builder()
                        .userEvaluationId(userEvaluationIds.get(evaluationFormIndex + optionIndex))
                        .userId(userId)
                        .perfumeId(perfumeId)
                        .field(EvaluationField.of(evaluationForm.fieldCode()))
                        .option(EvaluationOption.of(evaluationForm.optionCodes().get(optionIndex)))
                        .build());
            })
            .toList();

        userEvaluationRepository.create(userEvaluations);
    }

}
