package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ReviewMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ReviewMoodId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMoodJpaRepository extends JpaRepository<ReviewMoodJpaEntity, ReviewMoodId> {



}