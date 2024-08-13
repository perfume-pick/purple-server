package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeBrandJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeBrandJpaRepository extends JpaRepository<PerfumeBrandJpaEntity, String> {

    @Query(value = "SELECT * FROM perfume_brand ORDER BY display_order LIMIT :maxSize", nativeQuery = true)
    List<PerfumeBrandJpaEntity> getTopThirtyBy(int maxSize);

}
