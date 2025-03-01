package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewEvaluationId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewEvaluationJpaRepository extends
    JpaRepository<ReviewEvaluationJpaEntity, ReviewEvaluationId> {

    List<ReviewEvaluationJpaEntity> findByReviewId(Long reviewId);

    List<ReviewEvaluationJpaEntity> findByReviewIdOrderByFieldCodeAscOptionCodeAsc(Long reviewId);

    @Query("select re "
        + "from ReviewEvaluationJpaEntity re"
        + " left join ReviewJpaEntity r on r.id = re.reviewId "
        + "where r.perfumeJpaEntity.id = :perfumeId "
        + "order by re.fieldCode asc, re.optionCode asc ")
    List<ReviewEvaluationJpaEntity> findByPerfumeIdOrderByFieldCodeAscOptionCodeAsc(Long perfumeId);

}