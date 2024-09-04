package com.pikachu.purple.infrastructure.persistence.evaluation.adaptor;

import com.pikachu.purple.application.evaluation.port.out.EvaluationCodeRepository;
import com.pikachu.purple.domain.evaluation.EvaluationCode;
import com.pikachu.purple.infrastructure.persistence.evaluation.entity.EvaluationCodeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.evaluation.repository.EvaluationCodeJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationCodeJpaAdaptor implements EvaluationCodeRepository {

    private final EvaluationCodeJpaRepository evaluationCodeJpaRepository;

    @Override
    public List<EvaluationCode> findAll() {
        List<EvaluationCodeJpaEntity> evaluationCodeJpaEntities = evaluationCodeJpaRepository.findAll();

        return evaluationCodeJpaEntities.stream()
            .map(EvaluationCodeJpaEntity::toDomain)
            .toList();
    }


}
