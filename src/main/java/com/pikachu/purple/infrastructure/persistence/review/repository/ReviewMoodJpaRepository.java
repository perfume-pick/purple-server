package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewMoodId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewMoodJpaRepository extends JpaRepository<ReviewMoodJpaEntity, ReviewMoodId> {

    @Query("select rm "
        + "from ReviewMoodJpaEntity rm "
        + "where rm.reviewJpaEntity.id = :reviewId")
    List<ReviewMoodJpaEntity> findByReviewId(Long reviewId);

}
