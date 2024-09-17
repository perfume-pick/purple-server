package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StarRatingJpaRepository extends JpaRepository<StarRatingJpaEntity, Long> {

    @Query("select sr "
        + "from StarRatingJpaEntity sr "
        + "where sr.userJpaEntity.id = :userId")
    Optional<StarRatingJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

}
