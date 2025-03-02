package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewMoodId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewMoodJpaRepository extends JpaRepository<ReviewMoodJpaEntity, ReviewMoodId> {

    List<ReviewMoodJpaEntity> findByReviewId(Long reviewId);

    List<ReviewMoodJpaEntity> findAllByReviewId(Long reviewId);

}
