package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.BrandJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandJpaRepository extends JpaRepository<BrandJpaEntity, String> {

    @Query("select b from BrandJpaEntity b "
        + "left join fetch b.perfumes "
        + "where b.name in :names")
    List<BrandJpaEntity> findByNameIn(List<String> names);

}
