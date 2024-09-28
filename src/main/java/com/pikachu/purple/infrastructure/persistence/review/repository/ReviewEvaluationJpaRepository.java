package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewEvaluationId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewEvaluationJpaRepository extends
    JpaRepository<ReviewEvaluationJpaEntity, ReviewEvaluationId> {

    @Query("select re "
        + "from ReviewEvaluationJpaEntity re "
        + "where re.reviewJpaEntity.id = :reviewId")
    List<ReviewEvaluationJpaEntity> findByReviewId(Long reviewId);

}