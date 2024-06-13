package com.pikachu.purple.infrastructure.persistence.rating.repository;

import com.pikachu.purple.infrastructure.persistence.rating.entity.RatingJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingJpaRepository extends JpaRepository<RatingJpaEntity, Long> {

    List<RatingJpaEntity> findByUserId(Long userId);

}
