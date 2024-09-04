package com.pikachu.purple.application.evaluation.service.domain.impl;

import com.pikachu.purple.application.evaluation.port.out.EvaluationCodeRepository;
import com.pikachu.purple.application.evaluation.service.domain.EvaluationCodeDomainService;
import com.pikachu.purple.domain.evaluation.EvaluationCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationCodeDomainServiceImpl implements EvaluationCodeDomainService {

    private final EvaluationCodeRepository evaluationCodeRepository;

    @Override
    public List<EvaluationCode> findAll() {
        return evaluationCodeRepository.findAll();
    }

}
