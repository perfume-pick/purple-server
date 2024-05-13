package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeJpaRepository extends JpaRepository<PerfumeJpaEntity, Long> {

    @Query(value =
        "SELECT * FROM perfume p INNER JOIN perfume_brand pb ON p.p_brand_name = pb.brand_name WHERE p.p_brand_name IN :brandList",
        nativeQuery = true)
    List<PerfumeJpaEntity> findByBrandNameIn(List<String> brandList);

}
