package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ComplaintJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintJpaRepository extends JpaRepository<ComplaintJpaEntity, Long> {

    Optional<ComplaintJpaEntity> findByIdAndToken(Long complaintId, String token);

}
