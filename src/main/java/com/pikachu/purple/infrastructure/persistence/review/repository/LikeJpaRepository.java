package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.LikeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.LikeId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeJpaRepository extends JpaRepository<LikeJpaEntity, LikeId> {

    @Query("select l "
        + "from LikeJpaEntity l "
        + "where l.userJpaEntity.id = :userId and l.reviewJpaEntity.id = :reviewId")
    Optional<LikeJpaEntity> findByUserIdAndReviewId(Long userId, Long reviewId);

}
