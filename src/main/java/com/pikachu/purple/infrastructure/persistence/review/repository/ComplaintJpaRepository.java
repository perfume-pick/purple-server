package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ComplaintJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.ComplaintId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintJpaRepository extends JpaRepository<ComplaintJpaEntity, ComplaintId> {

}