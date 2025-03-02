package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.ComplaintJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComplaintJpaRepository extends JpaRepository<ComplaintJpaEntity, Long> {

    Optional<ComplaintJpaEntity> findByIdAndToken(Long complaintId, String token);

    Optional<ComplaintJpaEntity> findByReviewIdAndUserId(Long reviewId, Long userId);

    @Query("select c "
        + "from ComplaintJpaEntity c"
        + " left join ReviewJpaEntity r on r.id = c.reviewId "
        + "where c.userId = :userId and r.perfumeId = :perfumeId")
    List<ComplaintJpaEntity> findAllByUserIdAndPerfumeId(Long userId, Long perfumeId);
}
