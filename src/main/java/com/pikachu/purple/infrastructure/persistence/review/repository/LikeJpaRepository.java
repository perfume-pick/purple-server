package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.LikeJpaEntity;
import com.pikachu.purple.infrastructure.persistence.review.entity.id.LikeId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeJpaRepository extends JpaRepository<LikeJpaEntity, LikeId> {

    Optional<LikeJpaEntity> findByUserIdAndReviewId(Long userId, Long reviewId);

    List<LikeJpaEntity> findAllByReviewId(Long reviewId);

    @Query("select l "
        + "from LikeJpaEntity l"
        + " left join ReviewJpaEntity r on r.id = l.reviewId "
        + "where l.userId = :userId and r.perfumeId = :perfumeId")
    List<LikeJpaEntity> findAllByUserIdAndPerfumeId(Long userId, Long perfumeId);

}
