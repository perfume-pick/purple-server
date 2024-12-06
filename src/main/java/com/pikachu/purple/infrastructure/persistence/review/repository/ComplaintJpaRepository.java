package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ComplaintJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComplaintJpaRepository extends JpaRepository<ComplaintJpaEntity, Long> {

    Optional<ComplaintJpaEntity> findByIdAndToken(Long complaintId, String token);

    @Query("select cp "
        + "from ComplaintJpaEntity cp "
        + "where cp.reviewJpaEntity.id = :reviewId and cp.userJpaEntity.id = :userId")
    Optional<ComplaintJpaEntity> findByReviewIdAndUserId(Long reviewId, Long userId);

}
