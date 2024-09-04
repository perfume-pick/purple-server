package com.pikachu.purple.infrastructure.persistence.evaluation.repository;

import com.pikachu.purple.infrastructure.persistence.evaluation.entity.EvaluationFieldOptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationFieldOptionJpaRepository extends JpaRepository<EvaluationFieldOptionJpaEntity, Long> {

}
