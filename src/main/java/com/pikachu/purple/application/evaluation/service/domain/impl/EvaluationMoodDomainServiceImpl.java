package com.pikachu.purple.application.evaluation.service.domain.impl;

import com.pikachu.purple.application.evaluation.port.out.EvaluationMoodRepository;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationMoodDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationMoodDomainServiceImpl implements EvaluationMoodDomainService {

    private final EvaluationMoodRepository evaluationMoodRepository;

    @Override
    public List<EvaluationMood> findAll() {
        return evaluationMoodRepository.findAll();
    }
}
