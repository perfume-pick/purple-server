package com.pikachu.purple.infrastructure.persistence.review.repository;

import com.pikachu.purple.infrastructure.persistence.review.entity.StarRatingJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRatingJpaRepository extends JpaRepository<StarRatingJpaEntity, Long> {

    Optional<StarRatingJpaEntity> findByUserIdAndPerfumeId(Long userId, Long perfumeId);

    List<StarRatingJpaEntity> findAllByPerfumeId(Long perfumeId);

    List<StarRatingJpaEntity> findAllByUserId(Long userId);

}
