package com.pikachu.purple.infrastructure.persistence.evaluation.adaptor;

import com.pikachu.purple.application.evaluation.port.out.EvaluationMoodRepository;
import com.pikachu.purple.domain.evaluation.EvaluationMood;
import com.pikachu.purple.infrastructure.persistence.evaluation.entity.EvaluationMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.evaluation.repository.EvaluationMoodJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EvaluationMoodJpaAdaptor implements EvaluationMoodRepository {

    private final EvaluationMoodJpaRepository evaluationMoodJpaRepository;

    @Override
    public List<EvaluationMood> findAll() {
        List<EvaluationMoodJpaEntity> evaluationMoodJpaEntities = evaluationMoodJpaRepository.findAll();

        return evaluationMoodJpaEntities.stream()
            .map(EvaluationMoodJpaEntity::toDomain)
            .toList();
    }

}
