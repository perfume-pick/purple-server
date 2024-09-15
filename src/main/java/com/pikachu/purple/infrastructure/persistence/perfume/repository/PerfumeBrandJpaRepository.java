package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.BrandJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeBrandJpaRepository extends JpaRepository<BrandJpaEntity, String> {

    @Query(value = "SELECT * FROM perfume_brand ORDER BY display_order LIMIT :maxSize", nativeQuery = true)
    List<BrandJpaEntity> getTopThirtyBy(int maxSize);

}
