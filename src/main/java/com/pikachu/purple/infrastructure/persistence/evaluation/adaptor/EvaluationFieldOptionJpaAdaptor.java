package com.pikachu.purple.infrastructure.persistence.evaluation.adaptor;

import com.pikachu.purple.application.evaluation.port.out.EvaluationFieldOptionRepository;
import com.pikachu.purple.domain.evaluation.EvaluationFieldOption;
import com.pikachu.purple.infrastructure.persistence.evaluation.entity.EvaluationFieldOptionJpaEntity;
import com.pikachu.purple.infrastructure.persistence.evaluation.repository.EvaluationFieldOptionJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationFieldOptionJpaAdaptor implements EvaluationFieldOptionRepository {

    private final EvaluationFieldOptionJpaRepository evaluationFieldOptionJpaRepository;

    @Override
    public List<EvaluationFieldOption> findAll() {
        List<EvaluationFieldOptionJpaEntity> evaluationFieldOptionJpaEntities = evaluationFieldOptionJpaRepository.findAll();

        return evaluationFieldOptionJpaEntities.stream()
            .map(EvaluationFieldOptionJpaEntity::toDomain)
            .toList();
    }

}
