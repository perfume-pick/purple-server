package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewEvaluationId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewEvaluationJpaRepository extends
    JpaRepository<ReviewEvaluationJpaEntity, ReviewEvaluationId> {

    List<ReviewEvaluationJpaEntity> findByReviewEvalu(
        ReviewEvaluationId reviewEvaluationId);
}