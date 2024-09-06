package com.pikachu.purple.infrastructure.persistence.userevaluation.repository;

import com.pikachu.purple.infrastructure.persistence.userevaluation.entity.UserEvaluationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEvaluationJpaRepository extends JpaRepository<UserEvaluationJpaEntity, Long> {

}
