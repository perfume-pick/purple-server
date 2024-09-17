package com.pikachu.purple.application.userevaluation.service.domain.impl;

import com.pikachu.purple.application.userevaluation.port.out.ReviewEvaluationRepository;
import com.pikachu.purple.application.userevaluation.service.domain.ReviewEvaluationDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewEvaluationDomainServiceImpl implements ReviewEvaluationDomainService {

    private final ReviewEvaluationRepository reviewEvaluationRepository;

    @Override
    public void create(
        Long userId,
        Long perfumeId,
        List<EvaluationFieldVO> evaluationFieldVOs
    ) {
        List<Long> userEvaluationIds = evaluationFieldVOs.stream()
            .flatMap(evaluationForm -> IntStream.range(0, evaluationForm.optionCodes().size())
                .mapToObj(i -> IdGenerator.generate()))
            .toList();

        List<UserEvaluation> userEvaluations = evaluationFieldVOs.stream()
            .flatMap(evaluationForm -> {
                int evaluationFormIndex = evaluationFieldVOs.indexOf(evaluationForm);
                return IntStream.range(0, evaluationForm.optionCodes().size())
                    .mapToObj(optionIndex -> UserEvaluation.builder()
                        .userEvaluationId(userEvaluationIds.get(evaluationFormIndex + optionIndex))
                        .userId(userId)
                        .perfumeId(perfumeId)
                        .field(EvaluationFieldType.of(evaluationForm.fieldCode()))
                        .option(EvaluationOptionType.of(evaluationForm.optionCodes().get(optionIndex)))
                        .build());
            })
            .toList();

        reviewEvaluationRepository.create(userEvaluations);
    }

}
