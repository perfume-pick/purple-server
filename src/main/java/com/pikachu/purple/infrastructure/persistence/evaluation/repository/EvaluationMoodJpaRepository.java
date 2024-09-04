package com.pikachu.purple.infrastructure.persistence.evaluation.repository;

import com.pikachu.purple.infrastructure.persistence.evaluation.entity.EvaluationMoodJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationMoodJpaRepository extends JpaRepository<EvaluationMoodJpaEntity, String> {

}
