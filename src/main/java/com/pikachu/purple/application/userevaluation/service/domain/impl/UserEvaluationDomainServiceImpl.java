package com.pikachu.purple.application.userevaluation.service.domain.impl;

import com.pikachu.purple.application.userevaluation.port.out.UserEvaluationRepository;
import com.pikachu.purple.application.userevaluation.service.domain.UserEvaluationDomainService;
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
        List<Long> userEvaluationIds,
        Long userId,
        Long perfumeId,
        List<EvaluationForm> evaluationForms
    ) {
        List<UserEvaluation> userEvaluations = IntStream.range(0, userEvaluationIds.size())
            .mapToObj(i -> UserEvaluation.builder()
                .userEvaluationId(userEvaluationIds.get(i))
                .userId(userId)
                .perfumeId(perfumeId)
                .fieldCode(EvaluationField.of(evaluationForms.get(i).fieldCode()))
                .optionCode(EvaluationOption.of(evaluationForms.get(i).optionCodes().get(i)))
                .build())
            .toList();

        userEvaluationRepository.create(userEvaluations);
    }

}
