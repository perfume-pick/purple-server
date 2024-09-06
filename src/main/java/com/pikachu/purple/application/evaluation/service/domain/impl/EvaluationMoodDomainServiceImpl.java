package com.pikachu.purple.application.evaluation.service.domain.impl;

import com.pikachu.purple.application.evaluation.port.out.EvaluationMoodRepository;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationMoodDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationMoodDomainServiceImpl implements EvaluationMoodDomainService {

    private final EvaluationMoodRepository evaluationMoodRepository;

    @Override
    public void create(
        Long userId,
        Long perfumeId,
        List<String> moods
    ) {
        List<Long> evaluationMoodIds = IntStream.range(0, moods.size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        List<EvaluationMood> userEvaluationMoods = IntStream.range(0, evaluationMoodIds.size())
            .mapToObj(i -> EvaluationMood.builder()
                .evaluationMoodId(evaluationMoodIds.get(i))
                .userId(userId)
                .perfumeId(perfumeId)
                .moodName(moods.get(i))
                .build())
            .toList();

        evaluationMoodRepository.create(userEvaluationMoods);
    }

}
