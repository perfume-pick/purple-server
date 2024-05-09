package com.pikachu.purple.infrastructure.persistence.rating.repository;

import com.pikachu.purple.infrastructure.persistence.rating.entity.RatingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingJpaRepository extends JpaRepository<RatingJpaEntity, Long> {

}
