package com.pikachu.purple.application.evaluation.service.domain.impl;

import com.pikachu.purple.application.evaluation.port.out.EvaluationFieldOptionRepository;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationFieldOptionDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationFieldOption;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationFieldOptionDomainServiceImpl implements EvaluationFieldOptionDomainService {

    private final EvaluationFieldOptionRepository evaluationFieldOptionRepository;

    @Override
    public List<EvaluationFieldOption> findAll() {
        return evaluationFieldOptionRepository.findAll();
    }

}
